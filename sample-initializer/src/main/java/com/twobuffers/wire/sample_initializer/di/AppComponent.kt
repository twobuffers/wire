package com.twobuffers.wire.sample_initializer.di

import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.WireInitializerModule
import com.twobuffers.wire.sample_initializer.SampleApp
import com.twobuffers.wire.sample_initializer.sampleinitializers.AppInitializersModule
import com.twobuffers.wire.sample_initializer.ui.SampleActivityDiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@ApplicationScoped
@Component(
    modules = [
        // libraries - generic
        WireInitializerModule::class,
        // features
        SampleActivityDiModule::class,
        // other
        AppModule::class,
        AppInitializersModule::class,
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
