package com.twobuffers.wire.sample_firebase_messaging

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.EpoxyDataBindingPattern
import com.airbnb.epoxy.TypedEpoxyController
import com.twobuffers.wire.di.ActivityScoped
import com.twobuffers.wire.firebase_messaging.FirebaseMessagingReceivedMessages
import com.twobuffers.wire.firebase_messaging.FirebaseMessagingToken
import com.twobuffers.wire.sample_firebase_messaging.databinding.ActivitySample123Binding
import com.twobuffers.wire.utils.className
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class SampleActivity : DaggerAppCompatActivity() {
    private val binding: ActivitySample123Binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_sample123)
    }

    // STEP 4: use FirebaseMessagingToken and/or FirebaseMessagingReceivedMessages
    @Inject lateinit var token: FirebaseMessagingToken
    @Inject lateinit var firebaseRemoteMessages: FirebaseMessagingReceivedMessages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDeviceToken()
        setupReceivedMessagesList()
    }

    private fun logDeviceToken() {
        token.flow
            .onEach { Log.i(className, "New token: $it") }
            .launchIn(lifecycleScope)
    }

    private fun setupReceivedMessagesList() {
        val controller = SimpleListController().apply { isDebugLoggingEnabled = true }
        binding.recyclerView.adapter = controller.adapter
        // store the latest list in StateFlow
        val receivedMessages = MutableStateFlow(emptyList<String>())
        // collect Firebase remote messages
        firebaseRemoteMessages.flow
            .onEach { remoteMessage ->
                val notification = remoteMessage.notification ?: return@onEach
                receivedMessages.update { it + notification.body.toString() }
            }
            .launchIn(lifecycleScope)
        // on changes of the list update the adapter
        receivedMessages
            .onEach(controller::setData)
            .launchIn(lifecycleScope)
    }

    @Module @Suppress("unused")
    abstract class ContributeSubcomponentModule {
        @ContributesAndroidInjector
        @ActivityScoped
        abstract fun contributeSubcomponent(): SampleActivity
    }
}

@Suppress("unused")
@EpoxyDataBindingPattern(rClass = R::class, layoutPrefix = "view_holder")
@EpoxyDataBindingLayouts(value = [R.layout.view_holder_message])
internal interface EpoxyDataBindingConfig

internal class SimpleListController : TypedEpoxyController<List<String>>() {
    override fun buildModels(list: List<String>?) {
        (list ?: listOf()).forEach {
            message {
                id(it)
                text(it)
            }
        }
    }
}
