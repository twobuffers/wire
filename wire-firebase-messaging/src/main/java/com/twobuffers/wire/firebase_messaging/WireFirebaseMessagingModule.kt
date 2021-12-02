package com.twobuffers.wire.firebase_messaging

import com.google.firebase.messaging.FirebaseMessaging
import com.twobuffers.wire.coroutines.ProcessLifetimeCoroutineScope
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

const val FIREBASE_MESSAGING_COROUTINE_SCOPE = "FIREBASE_MESSAGING_COROUTINE_SCOPE"

@Module(
    includes = [
        WireFirebaseMessagingService.ContributeSubcomponentModule::class,
        FirebaseMessagingTokenInitializer.BindingModule::class,
    ]
)
object WireFirebaseMessagingModule {
    @Provides
    @Named(FIREBASE_MESSAGING_COROUTINE_SCOPE)
    fun provideMiddlewareCoroutineScope(
        @ProcessLifetimeCoroutineScope processScope: CoroutineScope
    ): CoroutineScope = processScope + SupervisorJob()

    @Provides
    fun providesFirebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()
}
