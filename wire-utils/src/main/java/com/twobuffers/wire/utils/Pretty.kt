package com.twobuffers.wire.utils

import com.tylerthrailkill.helpers.prettyprint.pp

fun <T> T.ppStr(
    indent: Int = 2,
    wrappedLineWidth: Int = 80,
): String {
    val stringBuilder = StringBuilder()
    pp(
        indent = indent,
        writeTo = stringBuilder,
        wrappedLineWidth = wrappedLineWidth,
    )
    return stringBuilder.toString()
}
