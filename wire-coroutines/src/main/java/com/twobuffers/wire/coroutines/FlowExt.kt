package com.twobuffers.wire.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private object UNDEFINED

// pairwise inspired by:
// https://rxjs.dev/api/operators/pairwise
// https://github.com/Kotlin/kotlinx.coroutines/issues/1767#issuecomment-577158308

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.pairwise(): Flow<Pair<T, T>> = flow {
    var prev: Any? = UNDEFINED
    collect { value ->
        if (prev !== UNDEFINED) emit(Pair(prev as T, value))
        prev = value
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.pairwiseStrict(): Flow<Pair<T, T>> = flow {
    var prev: Any? = UNDEFINED
    collect { value: T ->
        val v = if (prev === UNDEFINED) Pair(value, value) else Pair(prev as T, value)
        emit(v)
        prev = value
    }
}

fun <T> Flow<T>.toList(scope: CoroutineScope): Pair<List<T>, Job> {
    val dest = mutableListOf<T>()
    val job = onEach { dest.add(it) }.launchIn(scope)
    return Pair(dest, job)
}

fun <T> Flow<T>.filter(value: T) = filter { it == value }
