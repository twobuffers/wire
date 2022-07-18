package com.twobuffers.wire.utils

import org.junit.Test

class LoginExtTest {
    // there was an issue that if the address had leading zeros, we could have
    // Object@7d9d0818
    // Object@51001e5
    // Object@08ff24
    // Since the most common address takes 4 bytes, it makes sense to print them always under 8 characters.
    @Test
    fun `test 'address' result length is always the same`() {
        // given 100 random objects, when we check their address
        val address: List<String> = (1..100).map { Any().address }
        // then length will be the same for all
        val lengthsCount = address.map { it.length }.distinct().count()
        assert(lengthsCount == 1)
    }
}
