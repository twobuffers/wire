package com.twobuffers.wire.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.twobuffers.wire.utils.tryOrNull

fun <T> Moshi.adapterOrNull(clazz: Class<T>): JsonAdapter<T>? = tryOrNull { adapter(clazz) as JsonAdapter<T> }

fun <T> Moshi.hasAdapter(clazz: Class<T>): Boolean = adapterOrNull(clazz) != null
