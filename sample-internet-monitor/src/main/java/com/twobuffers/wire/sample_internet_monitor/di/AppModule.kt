// ktlint-disable max-line-length

@file:Suppress("unused")

package com.twobuffers.wire.sample_internet_monitor.di

import android.app.Application
import android.content.Context
import com.twobuffers.wire.sample_internet_monitor.SampleApp
import com.twobuffers.wire.sample_internet_monitor.di.AppModule.BindingModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        BindingModule::class,
    ]
)
object AppModule {
    @Module
    abstract class BindingModule {
        @Binds
        abstract fun bindApplication(instance: SampleApp): Application

        @Binds
        abstract fun bindContext(application: Application): Context
    }
}
