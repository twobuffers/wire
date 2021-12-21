package com.twobuffers.wire.utils.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

// Regular

fun Lifecycle.onEvent(event: Lifecycle.Event, block: (source: LifecycleOwner) -> Unit) {
    val o = LifecycleEventObserver { source, receivedEvent -> if (receivedEvent == event) block(source) }
    addObserver(o)
}

fun Fragment.onViewDestroy(block: (source: LifecycleOwner) -> Unit) =
    viewLifecycleOwner.lifecycle.onEvent(Lifecycle.Event.ON_DESTROY, block)

// Coroutines

fun AppCompatActivity.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

fun Fragment.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)
