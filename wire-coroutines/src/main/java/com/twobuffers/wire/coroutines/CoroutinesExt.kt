package com.twobuffers.wire.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun CoroutineScope.launchPeriodically(
    repeatMillis: Long,
    initialDelay: Long = 0L,
    block: suspend () -> Unit
) = launch {
    delay(initialDelay)
    while (isActive) {
        block()
        delay(repeatMillis)
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.every(repeatMillis: Long, initialDelay: Long = 0, noinline block: suspend () -> Unit): Job =
    launchPeriodically(initialDelay = initialDelay, repeatMillis = repeatMillis, block = block)

@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.everyHour(initialDelay: Long = 0, noinline block: suspend () -> Unit): Job =
    every(initialDelay, 60 * 60_000L, block)

fun every(repeatMillis: Long, initialDelay: Long = 0): Flow<Long> = flow {
    delay(initialDelay)
    while (currentCoroutineContext().isActive) {
        emit(System.currentTimeMillis())
        delay(repeatMillis)
    }
}
