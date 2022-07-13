package com.twobuffers.wire.utils.android

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InternetMonitorTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun call_isInternetAvailable() = runBlocking {
        val res1 = checkInternetWithDnsLookup()
        val res2 = checkInternetWithPing()
        val res3 = checkInternetWithHttp200()
        val res4 = checkInternetWithHttp204()
        println("$res1 $res2 $res3 $res4")
    }

    @Test
    fun call_test() = runBlocking {

    }
}