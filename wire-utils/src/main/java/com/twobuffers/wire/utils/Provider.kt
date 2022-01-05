package com.twobuffers.wire.utils

fun interface Provider0<R>  {
    fun get(): R
}

fun interface Provider1<R, A>  {
    fun get(a: A): R
}

fun interface Provider2<R, A, B>  {
    fun get(a: A, b: B): R
}
