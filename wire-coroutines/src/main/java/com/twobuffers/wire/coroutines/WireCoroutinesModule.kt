package com.twobuffers.wire.coroutines

import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module(includes = [WireCoroutinesCommonModule::class])
object WireCoroutinesModule {
    @Provides
    @ProcessLifetimeCoroutineScope
    fun provideProcessLifetimeCoroutineScope(): CoroutineScope =
        ProcessLifecycleOwner.get().lifecycle.coroutineScope
}
