package com.twobuffers.wire.sample_firebase_messaging.di

import com.twobuffers.wire.coroutines.WireCoroutinesModule
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.firebase_messaging.WireFirebaseMessagingModule
import com.twobuffers.wire.initializer.WireInitializerModule
import com.twobuffers.wire.sample_firebase_messaging.SampleApp
import com.twobuffers.wire.sample_firebase_messaging.SampleActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScoped
@Component(
    modules = [
        // libraries - generic
        WireInitializerModule::class,
        WireCoroutinesModule::class,
        WireFirebaseMessagingModule::class,
        // features
        SampleActivity.ContributeSubcomponentModule::class,
        // other
        AppModule::class,
        AndroidInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<SampleApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: SampleApp): AppComponent
    }
}

fun SampleApp.createComponent() = DaggerAppComponent.factory().create(this)
