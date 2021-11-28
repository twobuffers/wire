// ktlint-disable max-line-length

@file:Suppress("unused")

package com.twobuffers.wire.sample_firebaseconfig.di

import android.app.Application
import android.content.Context
import com.twobuffers.wire.firebaseconfig.FirebaseRemoteConfigInitializer
import com.twobuffers.wire.firebaseconfig.FirebaseRemoteConfigObserver
import com.twobuffers.wire.firebaseconfig.WireFirebaseRemoteConfigModule
import com.twobuffers.wire.firebaseconfig.WireFirebaseRemoteConfigObserverModule
import com.twobuffers.wire.sample_firebaseconfig.SampleApp
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
    @FirebaseRemoteConfigInitializer.DefaultConfigMap
    fun providesDefaultRemoteConfig(): Map<String, Any> = mapOf("feature_a_enabled" to false)

    @Provides
    @FirebaseRemoteConfigObserver.CheckInterval
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
