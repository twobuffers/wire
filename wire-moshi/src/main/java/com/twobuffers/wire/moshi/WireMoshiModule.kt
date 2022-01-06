package com.twobuffers.wire.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds

@Module(includes = [WireMoshiModule.BindingModule::class])
object WireMoshiModule {

    @Provides
    fun provideMoshi(
        factories: Set<@JvmSuppressWildcards JsonAdapter.Factory>
    ): Moshi = Moshi.Builder()
        .addMany(factories)
        .build()

    @Module
    abstract class BindingModule {
        @Multibinds
        abstract fun multibindJsonAdapterFactory(): Set<JsonAdapter.Factory>
    }
}

fun Moshi.Builder.addMany(factories: Set<JsonAdapter.Factory>) = apply { factories.forEach { add(it) } }
