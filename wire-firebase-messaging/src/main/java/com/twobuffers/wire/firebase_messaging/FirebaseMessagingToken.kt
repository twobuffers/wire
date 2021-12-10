package com.twobuffers.wire.firebase_messaging

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.twobuffers.wire.coroutines.ComputationDispatcher
import com.twobuffers.wire.di.ApplicationScoped
import com.twobuffers.wire.initializer.Initializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@ApplicationScoped
class FirebaseMessagingToken @Inject constructor() {
    private  val _stateFlow = MutableStateFlow<String?>(null)
    val flow: Flow<String> = _stateFlow.filterNotNull()

    suspend fun get() = flow.take(1).single()
    fun getOrNull() = _stateFlow.value

    fun set(token: String) = _stateFlow.update { token }
}

suspend fun FirebaseMessaging.getCurrentToken(): String? =
    try {
        token.await()
    } catch (e: Throwable) {
        Log.e("TAG", "Fetching FCM registration token failed", e)
        null
    }


class FirebaseMessagingTokenInitializer @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging,
    private val firebaseMessagingToken: FirebaseMessagingToken,
    @Named(FIREBASE_MESSAGING_COROUTINE_SCOPE) private val scope: CoroutineScope,
) : Initializer() {
    override fun init() = scope.launch {
        // TODO(greg): Doesn't seem to be the greatest solution. Review later.
        while (true) {
            val token = firebaseMessaging.getCurrentToken()
            if (token == null) {
                delay(1_000)
                continue
            }
            firebaseMessagingToken.set(token)
            break
        }
    }.let {}

    @Module
    abstract class BindingModule {
        @Binds
        @IntoSet
        abstract fun bindInitializer(b: FirebaseMessagingTokenInitializer): Initializer
    }
}
