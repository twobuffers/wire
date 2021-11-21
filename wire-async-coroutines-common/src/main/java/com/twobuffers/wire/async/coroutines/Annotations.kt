package com.twobuffers.wire.async.coroutines

import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Qualifier

// Annotations for Coroutine Dispatchers

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class ComputationDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class MainDispatcher

// Annotations for Coroutine Scopes

@DelicateCoroutinesApi
// @Global annotation should be used in only very special situations.
// Each use should be justified.
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class GlobalCoroutineScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class ProcessLifetimeCoroutineScope
