package com.twobuffers.wire.sample_initializer

import com.twobuffers.wire.initializer.Initializers
import com.twobuffers.wire.sample_initializer.di.createComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class SampleApp : DaggerApplication() {
    @Inject lateinit var initializers: Initializers

    override fun onCreate() {
        super.onCreate()
        initializers.init()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = createComponent()
}
