package com.twobuffers.wire.utils.android

import kotlinx.coroutines.runBlocking
import org.junit.Test

class InternetMonitorTest {

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