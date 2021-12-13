package com.twobuffers.wire.utils

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

// Inspired by:
// https://github.com/mfalcier/kotlin-memoize/blob/master/src/main/kotlin/com/mfalcier/memoize/Memoize.kt
// https://github.com/mfalcier/kotlin-memoize/blob/master/src/test/kotlin/com/mfalcier/memoize/MemoizeTest.kt

// Memoize regular functions

fun <R> Function0<R>.memoize(): Function0<R> {
    val value: R by lazy { this() }
    return { value }
}

fun <T, R> Function1<T, R>.memoize(): Function1<T, R> {
    val values = HashMap<T, R>()
    return { x -> values.getOrPut(x, { this(x) }) }
}

// Memoize suspend functions

fun <R> (suspend () -> R).memoize(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope,
): suspend () -> R {
    val fn: (suspend () -> R) = this
    val value: Deferred<R> = scope.async(context = context, start = LAZY, block = { fn() })
    return value::await
}
