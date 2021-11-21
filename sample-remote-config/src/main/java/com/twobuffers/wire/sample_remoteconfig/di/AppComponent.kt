package com.twobuffers.wire.sample_remoteconfig.di

import com.twobuffers.wire.async.coroutines.WireAsyncCoroutinesModule
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.WireInitializerModule
import com.twobuffers.wire.sample_remoteconfig.SampleApp
import com.twobuffers.wire.sample_remoteconfig.ui.SampleActivityDiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@ApplicationScoped
@Component(
    modules = [
        // libraries - generic
        WireAsyncCoroutinesModule::class,
        WireInitializerModule::class,
        // features
        SampleActivityDiModule::class,
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
