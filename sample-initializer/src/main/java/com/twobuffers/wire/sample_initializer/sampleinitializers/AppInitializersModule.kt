package com.twobuffers.wire.sample_initializer.sampleinitializers

import android.app.Application
import android.util.Log
import com.twobuffers.wire.initializer.WireInitializer
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object AppInitializersModule {
    @Provides
    @IntoSet
    fun provideTheVeryFirstInitializer(application: Application): WireInitializer =
        object : WireInitializer(priority = 0) {
            override fun init() {
                Log.d("AppInitializersModule","-- app: $application")
            }
        }
}
