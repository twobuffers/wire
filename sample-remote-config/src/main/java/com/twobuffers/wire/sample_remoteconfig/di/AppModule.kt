// ktlint-disable max-line-length

@file:Suppress("unused")

package com.twobuffers.wire.sample_remoteconfig.di

import android.app.Application
import android.content.Context
import com.twobuffers.wire.remoteconfig.FirebaseRemoteConfigDefaultConfigMap
import com.twobuffers.wire.remoteconfig.FirebaseRemoteConfigObserverCheckInterval
import com.twobuffers.wire.remoteconfig.WireFirebaseRemoteConfigModule
import com.twobuffers.wire.remoteconfig.WireFirebaseRemoteConfigObserverModule
import com.twobuffers.wire.sample_remoteconfig.SampleApp
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        WireFirebaseRemoteConfigModule::class,
        WireFirebaseRemoteConfigObserverModule::class,
        AppModule.BindingModule::class,
    ]
)
object AppModule {
    @Provides
    @FirebaseRemoteConfigDefaultConfigMap
    fun providesDefaultRemoteConfig(): Map<String, Any> = mapOf("feature_a_enabled" to false)

    @Provides
    @FirebaseRemoteConfigObserverCheckInterval
    fun providesRemoteConfigObserverIntervalInSecs(): Long = 10L
    // Warning:
    // It is not recommended to set such a short interval. It is set here to 10 seconds only for demo purposes.

    @Module
    abstract class BindingModule {
        @Binds
        abstract fun bindApplication(instance: SampleApp): Application

        @Binds
        abstract fun bindContext(application: Application): Context
    }
}
