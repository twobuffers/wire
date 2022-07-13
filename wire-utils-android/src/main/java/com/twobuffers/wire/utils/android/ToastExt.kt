package com.twobuffers.wire.utils.android

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

// Average reading speed is around 240 wpm, so 4 wps.
// Short Toast/Snackbar duration is 2 seconds, hence 8.
const val SHORT_TOAST_MSG_LIMIT = 8

val String.wordCount: Int get() = this.split(" ").count()

fun Context.showToast(message: String, length: Int? = null) {
    val displayLength = length ?:
    if (message.wordCount > SHORT_TOAST_MSG_LIMIT) Toast.LENGTH_LONG
    else Toast.LENGTH_SHORT
    Toast.makeText(this, message, displayLength).show()
}

fun Fragment.showToast(message: String, length: Int? = null) = requireContext().showToast(message, length)
