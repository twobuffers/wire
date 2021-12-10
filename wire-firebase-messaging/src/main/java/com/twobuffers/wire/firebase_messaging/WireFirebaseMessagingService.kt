package com.twobuffers.wire.firebase_messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.Module
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class WireFirebaseMessagingService : FirebaseMessagingService() {
    @Inject lateinit var token: FirebaseMessagingToken
    @Inject lateinit var receivedMessages: FirebaseMessagingReceivedMessages

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(newToken: String) = token.set(newToken)

    // There are two types of messages data messages and notification messages. Data messages are handled
    // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
    // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
    // is in the foreground. When the app is in the background an automatically generated notification is displayed.
    // When the user taps on the notification they are returned to the app. Messages containing both notification
    // and data payloads are treated as notification messages. The Firebase console always sends notification
    // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
    override fun onMessageReceived(msg: RemoteMessage) = receivedMessages.report(msg)

    @Module @Suppress("unused")
    abstract class ContributeSubcomponentModule {
        @ContributesAndroidInjector
        abstract fun contributeSubcomponent(): WireFirebaseMessagingService
    }
}
