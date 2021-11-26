package com.twobuffers.wire.firebaseconfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.WireInitializer
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import java.util.Optional
import javax.inject.Inject
import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class FirebaseRemoteConfigDefaultConfigMap

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class FirebaseRemoteConfigDefaultConfigXmlRes

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class FirebaseRemoteConfigMinFetchIntervalInSecs

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class FirebaseRemoteConfigInitializerPriority

class WireFirebaseRemoteConfigInitializer @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    @FirebaseRemoteConfigDefaultConfigMap private val defaultConfigMap: Optional<Map<String, Any>>,
    @FirebaseRemoteConfigDefaultConfigXmlRes private val defaultConfigXmlRes: Optional<Int>,
    @FirebaseRemoteConfigMinFetchIntervalInSecs private val minFetchIntervalInSecs: Optional<Long>,
    @FirebaseRemoteConfigInitializerPriority priority: Optional<Int>,
) : WireInitializer(priority.orElse(DEFAULT_PRIORITY)) {

    override fun init() {
        val settings = remoteConfigSettings {
            // Set minFetch to 0 seconds, unless specified an explicit value
            // Reasoning:
            // My apps fetch remote config at the app start and after that periodically,
            // e.g. every hour. Optionally, in some rare cases, I may request fetch explicitly,
            // and then, I want to the latest config, not the cached one. That's why I set it to 0.
            minimumFetchIntervalInSeconds = minFetchIntervalInSecs.orElse(0)
        }
        firebaseRemoteConfig.setConfigSettingsAsync(settings)
        // set default config (if available)
        if (defaultConfigMap.isPresent) firebaseRemoteConfig.setDefaultsAsync(defaultConfigMap.get())
        if (defaultConfigXmlRes.isPresent) firebaseRemoteConfig.setDefaultsAsync(defaultConfigXmlRes.get())
        // TODO(greg): Report failure of tasks here (setConfigSettingsAsync, setConfigSettingsAsync)
        // TODO(greg): Error Handler component is done, add reporting here these tasks' failures
    }

    companion object {
        const val DEFAULT_PRIORITY = 2
    }
}

@Module(includes = [WireFirebaseRemoteConfigModule.BindingModule::class])
object WireFirebaseRemoteConfigModule {

    @Provides
    @ApplicationScoped
    fun remoteFirebaseRemoteConfig(): FirebaseRemoteConfig = Firebase.remoteConfig

    @Module
    abstract class BindingModule {
        @BindsOptionalOf
        @FirebaseRemoteConfigDefaultConfigMap
        abstract fun bindOptionalDefaultConfigMap(): Map<String, Any>

        @BindsOptionalOf
        @FirebaseRemoteConfigDefaultConfigXmlRes
        abstract fun bindOptionalDefaultConfigXmlRes(): Int

        @BindsOptionalOf
        @FirebaseRemoteConfigMinFetchIntervalInSecs
        abstract fun bindOptionalMinFetchIntervalInSecs(): Long

        @BindsOptionalOf
        @FirebaseRemoteConfigInitializerPriority
        abstract fun bindOptionalInitializerPriority(): Long
    }
}
