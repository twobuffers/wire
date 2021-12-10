package com.twobuffers.wire.firebase_config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await

fun FirebaseRemoteConfig.fetchAndActivateWithCb(
    errorCb: (e: Exception) -> Unit = {},
    completedCb: (activated: Boolean) -> Unit
) {
    fetchAndActivate()
        .addOnCompleteListener { completedCb(it.result) }
        .addOnFailureListener(errorCb)
}

suspend fun FirebaseRemoteConfig.fetchAndActivateAwait(): Boolean = fetchAndActivate().await()
