package com.twobuffers.wire.utils

fun <T> tryOrNull(block: () -> T) =
    try {
        block()
    } catch (t: Throwable) {
        null
    }
