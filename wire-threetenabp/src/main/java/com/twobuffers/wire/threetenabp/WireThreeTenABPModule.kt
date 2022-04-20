package com.twobuffers.wire.threetenabp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.Initializer
import com.twobuffers.wire.threetenabp.WireThreeTenABPModule.BindingModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThreeTenABPInitializer(private val app: Application, priority: Int = 100): Initializer(priority) {
    private val _initialized = MutableStateFlow(false)
    val initialized: StateFlow<Boolean> = _initialized

    override fun init() {
        // https://github.com/JakeWharton/ThreeTenABP#usage
        AndroidThreeTen.init(app)
        _initialized.value = true
    }
}

@Module(includes = [BindingModule::class])
object WireThreeTenABPModule {
    @Provides @ApplicationScoped
    fun provideInitializer(app: Application): ThreeTenABPInitializer = ThreeTenABPInitializer(app)

    @Module
    abstract class BindingModule {
        @Binds @IntoSet abstract fun bindInitializer(b: ThreeTenABPInitializer): Initializer
    }
}
