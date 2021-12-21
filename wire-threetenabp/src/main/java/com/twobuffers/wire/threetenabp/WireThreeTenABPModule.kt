package com.twobuffers.wire.threetenabp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.twobuffers.wire.initializer.Initializer
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object WireThreeTenABPModule {
    @Provides
    @IntoSet
    fun provideInitializer(app: Application) = object : Initializer() {
        override fun init() {
            // https://github.com/JakeWharton/ThreeTenABP#usage
            AndroidThreeTen.init(app)
        }
    }
}
