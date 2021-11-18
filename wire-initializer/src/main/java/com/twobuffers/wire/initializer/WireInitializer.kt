package com.twobuffers.wire.initializer

import androidx.annotation.IntRange


abstract class WireInitializer(
    /** Priority value - helps to order launching Initializers.
     * Takes values from 0 to 127. */
    @IntRange(from = 0, to = 127) val priority: Int = DEFAULT_PRIORITY
) {
    abstract fun init()

    companion object {
        const val DEFAULT_PRIORITY = 64
    }
}
