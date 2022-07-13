package com.twobuffers.wire.sample_internet_monitor.ui

import com.twobuffers.wire.di.ActivityScoped
import com.twobuffers.wire.sample_internet_monitor.ui.SampleActivityDiModule.ActivitySubcomponentDiModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivitySubcomponentDiModule::class])
object SampleActivityDiModule {
    @Module
    abstract class ActivitySubcomponentDiModule {
        @ContributesAndroidInjector
        @ActivityScoped
        abstract fun contributeSubcomponent(): SampleActivity
    }
}
