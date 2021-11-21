package com.twobuffers.wire.remoteconfig

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.twobuffers.wire.async.coroutines.ComputationDispatcher
import com.twobuffers.wire.async.coroutines.ProcessLifetimeCoroutineScope
import com.twobuffers.wire.async.coroutines.utils.every
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.WireInitializer
import com.twobuffers.wire.utils.logTag
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.multibindings.IntoSet
import java.util.Optional
import javax.inject.Inject
import javax.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import kotlinx.coroutines.tasks.await

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class FirebaseRemoteConfigObserverCheckInterval

@ApplicationScoped
class FirebaseRemoteConfigObserver @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    @FirebaseRemoteConfigObserverCheckInterval private val checkIntervalInSecs: Optional<Long>,
    @ComputationDispatcher private val dispatcher: CoroutineDispatcher,
    @ProcessLifetimeCoroutineScope private val processLifetimeScope: CoroutineScope,
) {
    private val context = processLifetimeScope + dispatcher + SupervisorJob()

    private val _configValues = MutableStateFlow<Map<String, FirebaseRemoteConfigValue>>(emptyMap())
    val configValues = _configValues.asStateFlow()

    fun init() {
        val intervalInMillis = checkIntervalInSecs.orElse(DEFAULT_CHECK_INTERVAL) * 1000
        context.every(repeatMillis = intervalInMillis) {
            update()
            // TODO: replace this log with Timber
            Log.d(logTag, "Remote update done")
        }
        // TODO: handle a potential exception here
    }

    private suspend fun update(): Unit = try {
        val activated = firebaseRemoteConfig.fetchAndActivate().await()
        if (!activated) {
            Log.d(logTag, "Nothing changes since last fetch.")
        }
        _configValues.update { firebaseRemoteConfig.all }
    } catch (t: Throwable) {
        Log.e(logTag, "Error caught while updating remote config: $t")
        Unit
    }

    companion object {
        const val DEFAULT_CHECK_INTERVAL = 3600L // 1h
    }
}

class FirebaseRemoteConfigObserverInitializer @AssistedInject constructor(
    private val firebaseRemoteConfigObserver: FirebaseRemoteConfigObserver,
    @Assisted priority: Int = DEFAULT_PRIORITY,
) : WireInitializer(priority) {
    override fun init() {
        firebaseRemoteConfigObserver.init()
    }

    @AssistedFactory
    interface Factory {
        fun create(priority: Int): FirebaseRemoteConfigObserverInitializer
    }
}

// This module depends on WireFirebaseRemoteConfigModule, so the latter must be also included in the Dagger component.
@Module(includes = [WireFirebaseRemoteConfigObserverModule.BindingModule::class])
object WireFirebaseRemoteConfigObserverModule {

    @Provides
    @IntoSet
    fun provideInitializer(f: FirebaseRemoteConfigObserverInitializer.Factory): WireInitializer = f.create(priority = 1)

    @Module
    abstract class BindingModule {
        @BindsOptionalOf
        @FirebaseRemoteConfigObserverCheckInterval
        abstract fun bindOptionalMinFetchIntervalInSecs(): Long
    }
}
