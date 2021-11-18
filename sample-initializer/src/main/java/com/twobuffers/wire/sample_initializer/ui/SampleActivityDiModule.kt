package com.twobuffers.wire.sample_initializer.ui

import com.twobuffers.wire.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [SampleActivityDiModule.ActivitySubcomponentDiModule::class])
object SampleActivityDiModule {
    @Module
    abstract class ActivitySubcomponentDiModule {
        @ContributesAndroidInjector
        @ActivityScoped
        abstract fun contributeSubcomponent(): SampleActivity
    }
}
