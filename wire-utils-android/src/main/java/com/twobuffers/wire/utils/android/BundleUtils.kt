package com.twobuffers.wire.utils.android

import android.os.Bundle

fun mapStrStrToBundle(map: Map<String, String>): Bundle {
    val bundle = Bundle()
    map.forEach { bundle.putString(it.key, it.value) }
    return bundle
}

fun mapStrAnyToBundle(map: Map<String, Any>): Bundle {
    val bundle = Bundle()
    map.forEach { (k, v) ->
        when (v) {
            is String -> bundle.putString(k, v)
            is Boolean -> bundle.putBoolean(k, v)
            is Int -> bundle.putInt(k, v)
            is Long -> bundle.putLong(k, v)
            is Float -> bundle.putFloat(k, v)
            is Double -> bundle.putDouble(k, v)
            else -> error("unsupported type")
        }
    }
    return bundle
}

fun Map<String, String>.toStrStrBundle(): Bundle = mapStrStrToBundle(this)
fun Map<String, Any>.toStrAnyBundle(): Bundle = mapStrAnyToBundle(this)
