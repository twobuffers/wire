package com.twobuffers.wire.utils

import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MemoizeTest {

    @Test
    fun memoizeFun0() {
        // given
        val counter = AtomicInteger(0)
        fun takeFirstName(): String = "Mika".also { counter.incrementAndGet() }
        val memoizedTakeFirstName = ::takeFirstName.memoize()
        // rest
        assertEquals("Mika", memoizedTakeFirstName())
        assertEquals("Mika", memoizedTakeFirstName())
        assertEquals("Mika", memoizedTakeFirstName())
        assertEquals(1, counter.get())
    }

    @Test
    fun memoizeFun1_forTwoDifferentCallsTwoCallsOnly_string() {
        val counter = AtomicInteger(0)
        // GIVEN a fn
        fun addSemicolon(param: String): String = "$param;".also { counter.incrementAndGet() }
        // WHEN the fn is memoized and called three times with two different data inputs
        val memoizedAddSemicolon = ::addSemicolon.memoize()
        assertEquals("One;", memoizedAddSemicolon("One"))
        assertEquals("Two;", memoizedAddSemicolon("Two"))
        assertEquals("Two;", memoizedAddSemicolon("Two"))
        // THEN it looks it's executed only twice
        assertEquals(2, counter.get())
    }

    @Test
    fun memoizeFun1_forTwoDifferentCallsTwoCallsOnly_dataClass() {
        val counter = AtomicInteger(0)
        // GIVEN a fn
        data class FillMe (val field: String?)
        fun addSemicolon(param: FillMe) = param.copy(field = "${param.field};").also { counter.incrementAndGet() }
        // WHEN the fn is memoized and called three times with two different data inputs
        val memoizedAddSemicolon = ::addSemicolon.memoize()
        assertEquals(FillMe("one;"), memoizedAddSemicolon(FillMe("one")))
        assertEquals(FillMe("two;"), memoizedAddSemicolon(FillMe("two")))
        assertEquals(FillMe("two;"), memoizedAddSemicolon(FillMe("two")))
        // THEN it looks it's executed only twice
        assertEquals(2, counter.get())
    }

    @Test
    fun memoizeSuspendFun0_memoizedFnButZeroCalls() = runBlockingTest {
        val testDispatcher = TestCoroutineDispatcher()
        val counter = AtomicInteger(0)
        // GIVEN a function
        suspend fun takeFirstName(): String { delay(1_000); return "Mika".also { counter.incrementAndGet() } }
        // WHEN the fn is memoized
        @Suppress("UNUSED_VARIABLE")
        val memoizedTakeFirstName = ::takeFirstName.memoize(testDispatcher, this)
        // THEN it looks it's never executed
        assertEquals(0, counter.get())
    }

    @Test
    fun memoizeSuspendFun0_threeCallsButOnlyOneExecution() = runBlockingTest {
        val testDispatcher = TestCoroutineDispatcher()
        val counter = AtomicInteger(0)
        // GIVEN a function
        suspend fun takeFirstName(): String { delay(1_000); return "Mika".also { counter.incrementAndGet() } }
        // WHEN the fn is memoize and called three times
        val memoizedTakeFirstName = ::takeFirstName.memoize(testDispatcher, this)
        launch { assertEquals("Mika", memoizedTakeFirstName()) }
        launch { assertEquals("Mika", memoizedTakeFirstName()) }
        launch { assertEquals("Mika", memoizedTakeFirstName()) }
        testDispatcher.advanceTimeBy(2_000)
        // THEN it looks it is executed only once
        assertEquals(1, counter.get())
    }
}
