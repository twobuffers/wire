package com.twobuffers.wire.initializer

import androidx.annotation.IntRange
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Inject

abstract class Initializer(
    /** Priority value - helps to order launching Initializers.
     * Takes values from 0 to 127. */
    @IntRange(from = 0, to = 127) val priority: Int = DEFAULT_PRIORITY
) {
    abstract fun init()

    companion object {
        const val DEFAULT_PRIORITY = 64
    }
}

class Initializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards Initializer>
) {
    fun init() {
        initializers
            .sortedBy { it.priority }
            .forEach { it.init() }
    }
}

@Module
abstract class WireInitializerModule {
    // The declaration of multi-binding is necessary in case
    // if there is no bindings and set is empty.
    @Multibinds
    abstract fun bindInitializersSet(): Set<Initializer>
}
