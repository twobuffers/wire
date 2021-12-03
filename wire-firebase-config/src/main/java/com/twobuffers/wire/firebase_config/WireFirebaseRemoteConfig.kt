package com.twobuffers.wire.firebase_config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.Initializer
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.Optional
import javax.inject.Inject
import javax.inject.Qualifier

class FirebaseRemoteConfigInitializer @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    @DefaultConfigMap private val defaultConfigMap: Optional<Map<String, Any>>,
    @DefaultConfigXmlRes private val defaultConfigXmlRes: Optional<Int>,
    @MinFetchIntervalInSecs private val minFetchIntervalInSecs: Optional<Long>,
    @Priority priority: Optional<Int>,
) : Initializer(priority.orElse(DEFAULT_PRIORITY)) {

    override fun init() {
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = minFetchIntervalInSecs.orElse(DEFAULT_MIN_FETCH_INTERVAL_IN_SECS)
        }
        firebaseRemoteConfig.setConfigSettingsAsync(settings)
        // set default config (if available)
        if (defaultConfigMap.isPresent) firebaseRemoteConfig.setDefaultsAsync(defaultConfigMap.get())
        if (defaultConfigXmlRes.isPresent) firebaseRemoteConfig.setDefaultsAsync(defaultConfigXmlRes.get())
        // TODO(greg): Report failure of tasks here (setConfigSettingsAsync, setConfigSettingsAsync)
        // TODO(greg): Error Handler component is done, add reporting here these tasks' failures
    }

    companion object {
        const val DEFAULT_PRIORITY = 5
        // Set minFetch to 0 seconds, unless specified an explicit value
        // My apps fetch remote config at the app start and after that periodically,
        // e.g. every hour. Optionally, in some rare cases, I may request fetch explicitly,
        // and then, I want to the latest config, not the cached one. That's why I set it to 0.
        // OTOH according to the docs, fetching too often might lead to FirebaseRemoteConfigFetchThrottledException:
        // https://firebase.google.com/docs/remote-config/get-started?platform=android#throttling
        // So if you  think you might be requesting fetch explicitly, might need to set this value.
        const val DEFAULT_MIN_FETCH_INTERVAL_IN_SECS = 0L
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    @MustBeDocumented
    annotation class DefaultConfigMap

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    @MustBeDocumented
    annotation class DefaultConfigXmlRes

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    @MustBeDocumented
    annotation class MinFetchIntervalInSecs

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    @MustBeDocumented
    annotation class Priority

    @Module
    abstract class BindingModule {
        @Binds
        @IntoSet
        abstract fun bindInitializer(b: FirebaseRemoteConfigInitializer): Initializer

        @BindsOptionalOf
        @DefaultConfigMap
        abstract fun bindOptionalDefaultConfigMap(): Map<String, Any>

        @BindsOptionalOf
        @DefaultConfigXmlRes
        abstract fun bindOptionalDefaultConfigXmlRes(): Int

        @BindsOptionalOf
        @MinFetchIntervalInSecs
        abstract fun bindOptionalMinFetchIntervalInSecs(): Long

        @BindsOptionalOf
        @Priority
        abstract fun bindOptionalPriority(): Int
    }
}

@Module(includes = [FirebaseRemoteConfigInitializer.BindingModule::class])
object WireFirebaseRemoteConfigModule {

    @Provides
    @ApplicationScoped
    fun remoteFirebaseRemoteConfig(): FirebaseRemoteConfig = Firebase.remoteConfig
    // FirebaseInitProvider handles initializing FirebaseApp.
    // No longer need to call FirebaseApp.initializeApp, unless we support an unusual case.
    // https://firebase.google.com/docs/reference/android/com/google/firebase/FirebaseApp
    // https://firebase.google.com/docs/reference/android/com/google/firebase/provider/FirebaseInitProvider
}
