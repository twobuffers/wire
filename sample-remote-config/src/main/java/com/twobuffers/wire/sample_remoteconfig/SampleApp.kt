package com.twobuffers.wire.sample_remoteconfig

import com.twobuffers.wire.initializer.WireInitializers
import com.twobuffers.wire.sample_remoteconfig.di.createComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class SampleApp : DaggerApplication() {
    @Inject lateinit var initializers: WireInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = createComponent()
}
