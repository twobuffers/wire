package com.twobuffers.wire.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun CoroutineScope.launchPeriodically(
    initialDelay: Long,
    repeatMillis: Long,
    block: suspend () -> Unit
) = launch {
    delay(initialDelay)
    while (isActive) {
        block()
        delay(repeatMillis)
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.every(repeatMillis: Long, initialDelay: Long = 0, noinline block: suspend () -> Unit) =
    launchPeriodically(initialDelay = initialDelay, repeatMillis = repeatMillis, block = block)

@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.everyHour(initialDelay: Long = 0, noinline block: suspend () -> Unit) =
    every(initialDelay, 60 * 60_000L, block)

fun every(repeatMillis: Long): Flow<Long> = flow {
    while (currentCoroutineContext().isActive) {
        emit(System.currentTimeMillis())
        delay(repeatMillis)
    }
}
