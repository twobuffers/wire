package com.twobuffers.wire.async.coroutines

import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

@Module
object WireAsyncCoroutinesModule {
    @Provides
    @ComputationDispatcher
    fun provideComputationDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @DelicateCoroutinesApi
    @Provides
    @GlobalCoroutineScope
    fun provideGlobalCoroutineScope(): CoroutineScope = GlobalScope

    @Provides
    @ProcessLifetimeCoroutineScope
    fun provideProcessLifetimeCoroutineScope(): CoroutineScope =
        ProcessLifecycleOwner.get().lifecycle.coroutineScope
}
