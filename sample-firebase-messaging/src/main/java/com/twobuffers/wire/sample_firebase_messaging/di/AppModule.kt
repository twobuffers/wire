// ktlint-disable max-line-length

@file:Suppress("unused")

package com.twobuffers.wire.sample_firebase_messaging.di

import android.app.Application
import android.content.Context
import com.twobuffers.wire.sample_firebase_messaging.SampleApp
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        AppModule.BindingModule::class,
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
