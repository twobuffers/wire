package com.twobuffers.wire.utils.android

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService
import com.twobuffers.wire.utils.address
import kotlin.math.max
import timber.log.Timber

private var ownerTypeLength = 9
private var sourceLength = 30
private var eventLength = 10

fun createLogLine(
    source: LifecycleOwner,
    event: Lifecycle.Event,
    ownerType: String,
    sourcePrintedFn: ((lifecycleOwner: LifecycleOwner) -> String)? = null,
    extraPrintedFn: ((lifecycleOwner: LifecycleOwner) -> String)? = null,
): String {
    val sourcePrinted = if (sourcePrintedFn != null) sourcePrintedFn(source) else source.address
    @Suppress("UnnecessaryVariable") val ownerTypePrinted = ownerType
    val eventPrinted = event.toString()
    ownerTypeLength = max(ownerTypePrinted.length, ownerTypeLength)
    sourceLength = max(sourcePrinted.length, sourceLength)
    eventLength = max(eventPrinted.length, eventLength)
    val sourceFinal = sourcePrinted.padStart(sourceLength)
    val ownerTypeFinal = ownerTypePrinted.padEnd(ownerTypeLength)
    val eventFinal = eventPrinted.padEnd(eventLength)
    val extraSeparator = if (extraPrintedFn != null)  " - " else ""
    val extra = if (extraPrintedFn != null) extraPrintedFn(source) else ""
    return "[LIFECYCLE] $sourceFinal - $ownerTypeFinal $eventFinal$extraSeparator$extra"
}

fun logLifecycleEvents(lifecycle: Lifecycle, ownerType: String,
                       extraPrinted: ((lifecycleOwner: LifecycleOwner) -> String)? = null,
                       sourcePrinted: ((lifecycleOwner: LifecycleOwner) -> String)? = null,) {
    lifecycle.addObserver(
        LifecycleEventObserver { source, receivedEvent ->
            Timber.d(createLogLine(source, receivedEvent, ownerType, sourcePrinted, extraPrinted))
        }
    )
}

fun logActivityLifecycleEvents(activity: ComponentActivity) {
    logLifecycleEvents(lifecycle = activity.lifecycle, ownerType = "ACTIVITY")
}

fun logFragmentLifecycleEvents(fragment: Fragment) {
    logLifecycleEvents(
        lifecycle = fragment.lifecycle,
        ownerType = "FRAGMENT",
        extraPrinted = { "activity=${fragment.requireActivity().address}" }
    )
}

fun logFragmentViewLifecycleEvents(fragment: Fragment) {
    logLifecycleEvents(
        lifecycle = fragment.viewLifecycleOwner.lifecycle,
        ownerType = "VIEW",
        sourcePrinted = { fragment.address },
        extraPrinted = { s -> "activity=${fragment.requireActivity().address}, source=${s.address}" }
    )
}

fun logServiceLifecycleEvents(service: LifecycleService) {
    logLifecycleEvents(lifecycle = service.lifecycle, ownerType = "SERVICE")
}
