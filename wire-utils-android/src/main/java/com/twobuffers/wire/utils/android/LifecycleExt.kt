package com.twobuffers.wire.utils.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

// Regular

fun Lifecycle.onEvent(event: Event, block: (source: LifecycleOwner) -> Unit) {
    val o = LifecycleEventObserver { source, receivedEvent -> if (receivedEvent == event) block(source) }
    addObserver(o)
}

fun Fragment.onViewDestroy(block: (source: LifecycleOwner) -> Unit) =
    viewLifecycleOwner.lifecycle.onEvent(Event.ON_DESTROY, block)

// Coroutines

fun AppCompatActivity.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

fun Fragment.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

// Utilities

fun RecyclerView.clearAdapterOnViewDestroy(viewLifecycleOwner: LifecycleOwner) =
    viewLifecycleOwner.lifecycle.onEvent(Event.ON_DESTROY) { adapter = null }

fun ViewPager2.clearAdapterOnViewDestroy(viewLifecycleOwner: LifecycleOwner) =
    viewLifecycleOwner.lifecycle.onEvent(Event.ON_DESTROY) { adapter = null }
