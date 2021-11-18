package com.twobuffers.wire.initializer

import javax.inject.Inject

class WireInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards WireInitializer>
) {
    fun init() {
        initializers
            .sortedBy { it.priority }
            .forEach { it.init() }
    }
}
