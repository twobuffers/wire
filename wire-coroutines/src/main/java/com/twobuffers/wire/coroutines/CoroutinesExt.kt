package com.twobuffers.wire.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

suspend fun CoroutineScope.every(
    repeatMillis: Long,
    initialDelay: Long = 0L,
    block: suspend (Long) -> Unit,
) {
    delay(initialDelay)
    while (isActive) {
        block(System.currentTimeMillis())
        delay(repeatMillis)
    }
}

fun CoroutineScope.everyJob(
    repeatMillis: Long,
    initialDelay: Long = 0L,
    block: suspend (Long) -> Unit,
): Job = launch { every(repeatMillis = repeatMillis, initialDelay = initialDelay, block = block) }

fun everyFlow(repeatMillis: Long, initialDelay: Long = 0): Flow<Long> = flow {
    delay(initialDelay)
    while (currentCoroutineContext().isActive) {
        emit(System.currentTimeMillis())
        delay(repeatMillis)
    }
}

private const val second = 1000L
private const val minute = 60 * second
private const val hour = 60 * minute

fun CoroutineScope.everyMinuteJob(initialDelay: Long = 0, block: suspend (Long) -> Unit): Job =
    everyJob(repeatMillis = minute, initialDelay = initialDelay, block = block)

fun CoroutineScope.everyHourJob(initialDelay: Long = 0, block: suspend (Long) -> Unit): Job =
    everyJob(repeatMillis = hour, initialDelay = initialDelay, block = block)

fun everyMinuteFlow(initialDelay: Long = 0): Flow<Long> = everyFlow(repeatMillis = minute, initialDelay = initialDelay)
fun everyHourFlow(initialDelay: Long = 0): Flow<Long> = everyFlow(repeatMillis = hour, initialDelay = initialDelay)
