package com.twobuffers.wire.firebase_messaging

import com.google.firebase.messaging.RemoteMessage
import com.twobuffers.wire.di.ApplicationScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update

@ApplicationScoped
class FirebaseMessagingReceivedMessages @Inject constructor() {
    private  val _stateFlow = MutableStateFlow<RemoteMessage?>(null)
    val flow: Flow<RemoteMessage> = _stateFlow.filterNotNull()

    fun report(remoteMessage: RemoteMessage) = _stateFlow.update { remoteMessage }
}
