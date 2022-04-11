package com.twobuffers.wire.coroutines

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlowExtTest {

    // pairwise

    @Test
    fun `pairwise - simple scenario`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = (1..3).asFlow()
        // when
        flow.pairwise().collect { values.add(it) }
        // then
        assertThat(values).isEqualTo(listOf(Pair(1, 2), Pair(2, 3)))
    }

    @Test
    fun `pairwise - one element emitted`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = listOf(1).asFlow()
        // when
        flow.pairwise().collect { values.add(it) }
        // then
        assertThat(values).isEmpty()
    }

    @Test
    fun `pairwise - time elapsed before emitting second`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = (1..3).asFlow().onEach { delay(200) }
        // when
        flow
            .pairwise()
            .onEach { values.add(it) }
            .launchIn(this)
        advanceTimeBy(100)
        // then
        assertThat(values).isEmpty()
    }

    // pairwiseStrict

    @Test
    fun `pairwiseStrict - simple scenario`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = (1..3).asFlow()
        // when
        flow.pairwiseStrict().collect { values.add(it) }
        // then
        assertThat(values).containsExactly(Pair(1, 1), Pair(1, 2), Pair(2, 3))
    }

    @Test
    fun `pairwiseStrict - one element emitted`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = listOf(1).asFlow()
        // when
        flow.pairwiseStrict().collect { values.add(it) }
        // then
        assertThat(values).containsExactly(Pair(1,1))
    }

    @Test
    fun `pairwiseStrict - time elapsed before emitting second`() = runTest {
        val values = mutableListOf<Pair<Int, Int>>()
        // given
        val flow = (1..3).asFlow().onEach { delay(200) }
        // when
        flow
            .pairwiseStrict()
            .onEach { values.add(it) }
            .launchIn(this)
        advanceTimeBy(100)
        // then
        assertThat(values).isEmpty()
    }
}
