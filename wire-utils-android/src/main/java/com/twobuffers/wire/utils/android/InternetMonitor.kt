package com.twobuffers.wire.utils.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.twobuffers.wire.coroutines.everyFlow
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.net.URL
import java.sql.Time
import java.sql.Timestamp
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

// https://stackoverflow.com/a/37868059/3023806

// https://github.com/novoda/merlin/blob/release/core/src/main/java/com/novoda/merlin/Endpoint.java#L8

// https://stackoverflow.com/questions/17880287/android-programmatically-check-internet-connection-and-display-dialog-if-notco
// https://stackoverflow.com/questions/11506321/how-to-ping-an-ip-address
// https://github.com/novoda/merlin/blob/release/core/src/main/java/com/novoda/merlin/Endpoint.java#L8
// https://gist.github.com/kavanmevada/20458c1e99f6d750bf122c3f975d0396
// https://www.geeksforgeeks.org/pinging-ip-address-java/

// https://gist.github.com/kavanmevada/20458c1e99f6d750bf122c3f975d0396

// https://stackoverflow.com/questions/313712/what-is-the-best-method-to-ping-in-c-under-linux
// https://git.busybox.net/busybox/tree/networking/ping.c

// https://fasterthanli.me/series/making-our-own-ping/part-1
// https://crates.io/crates/icmp-socket
// https://security.googleblog.com/2021/05/integrating-rust-into-android-open.html?m=1

// https://stackoverflow.com/questions/9570237/android-check-internet-connection

fun isNetworkAvailable(ctx: Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager? ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val networks = cm.allNetworks
        networks.forEach { n ->
            val nInfo = cm.getNetworkInfo(n)
            if (nInfo != null && nInfo.isConnected) return@isNetworkAvailable true
        }
    } else {
        val networks = cm.allNetworkInfo
        networks.forEach { nInfo ->
            if (nInfo != null && nInfo.isConnected) return@isNetworkAvailable true
        }
    }
    return false
}

fun checkInternetWithDnsLookup(hostname: String = "google.com"): Boolean =
    try {
        val ipAddr = InetAddress.getByName(hostname)
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }

// When tested on V2 without HTTP Toolkit intercepting it returned false.
// When I started intercepting traffic it worked fine - returned true.
fun checkInternetWithPing(ipAddress: ByteArray = byteArrayOf(8,8,8,8)): Boolean =
    try {
        val ipAddr = InetAddress.getByAddress(ipAddress)
        ipAddr.isReachable(1000)
    } catch (e: Exception) {
        false
    }

// Disadvantage: it fetches the website
fun checkInternetWithHttp200(endpoint: String = "https://www.google.com/") = try {
    val url = URL(endpoint)
    val urlc = (url.openConnection() as HttpURLConnection)
        .apply {
            setRequestProperty("User-Agent", "InternetMonitor/1.0.1")
            setRequestProperty("Connection", "close")
            connectTimeout = 1000
        }
    urlc.connect()
    urlc.responseCode == HttpURLConnection.HTTP_OK
} catch (e: IOException) {
    false
}

// based on 204 endpoints:
// - http://google.com/generate_204
// - https://connectivitycheck.android.com/generate_204
fun checkInternetWithHttp204(endpoint: String = "https://google.com/generate_204") = try {
    val url = URL(endpoint)
    val time0 = System.currentTimeMillis()
    val urlc: HttpURLConnection = (url.openConnection() as HttpURLConnection)
        .apply {
            setRequestProperty("User-Agent", "test")
            setRequestProperty("Connection", "close")
            connectTimeout = 1000
            readTimeout = 1000
//            useCaches = false
//            defaultUseCaches = false
        }
    val time1 = System.currentTimeMillis()
    urlc.connect()
    val time2 = System.currentTimeMillis()
    val code = urlc.responseCode
    val time3 = System.currentTimeMillis()
    println("time0 = %5d".format(time1-time0))
    println("time1 = %5d".format(time2-time1))
    println("time2 = %5d".format(time3-time2))
    code == HttpURLConnection.HTTP_NO_CONTENT
} catch (e: IOException) {
    Log.d("InternetMonitor", "check - caught1: ${e}")
    println("caught: $e")
    false
} catch (e: Exception) {
    Log.d("InternetMonitor", "check - caught2: ${e}")
    println("caught: $e")
    false
}

val client = OkHttpClient.Builder()
    .connectTimeout(1000, MILLISECONDS)
    .readTimeout(1000, MILLISECONDS)
    .cache(null)
    .build()

fun checkInternetWithHttp204AndOkHttp(
    endpoint: String = "https://google.com/generate_204",
    okHttpClient: OkHttpClient = client,
): Boolean = try {
    val request = Request.Builder().url(endpoint).build()
    val call = okHttpClient.newCall(request)
    val time1 = System.currentTimeMillis()
    val response = call.execute()
    val time2 = System.currentTimeMillis()
    val code = response.code
    val time3 = System.currentTimeMillis()
    println("time1 = %5d".format(time2-time1))
    println("time2 = %5d".format(time3-time2))
    code == 204
} catch (e: SocketTimeoutException) {
    Log.d("InternetMonitor", "check - caught: ${e}")
    println("caught: ${e.message}, ${e.bytesTransferred}")
    false
} catch (e: Throwable) {
    Log.d("InternetMonitor", "check - caught: ${e}")
    println("caught: ${e.message}")
    false
}


// -------

@OptIn(DelicateCoroutinesApi::class)
data class Config(
    val context: Context,
    val host: String = "google.com",
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    val scope: CoroutineScope = GlobalScope,
    val delayWhenConnectedMs: Long = 5_000,
    val delayWhenDisconnectedMs: Long = 500,
)

sealed class Status {
    sealed class Disconnected : Status() {
        object NetworkMissing : Disconnected()
        object InternetMissing : Disconnected()
    }
    object Connected : Status()
}

fun delayForStatus(status: Status, config: Config): Long =
    when (status) {
        is Status.Disconnected -> config.delayWhenDisconnectedMs
        is Status.Connected -> config.delayWhenConnectedMs
    }

// Rename to NetStatus, NetState, Netmo

object InternetMonitor {
    lateinit var config: Config

    private val _status = MutableSharedFlow<Status>()
    val status: Flow<Status> = _status
    val connected: Flow<Boolean> = status.map { it is Status.Connected }

    fun init(config: Config) {
        this.config = config

        _status
            .onStart { emit(Status.Disconnected.NetworkMissing) }
            .distinctUntilChanged()
            .onEach { Log.d("InternetMonitor", "flow - status: ${it::class.simpleName}") }
            .map { s -> delayForStatus(s, config) }
            .onEach { Log.d("InternetMonitor", "flow - delay: $it") }
            .flatMapLatest {
                everyFlow(it, initialDelay = 0)
                    .onEach {
                        Log.d("InternetMonitor", "flow - check status at: ${Time(Timestamp(it).time)}")
                        checkStatus()
                    }
            }
            .launchIn(config.scope)
    }

    suspend fun checkStatus() = withContext(config.ioDispatcher) {
        val result =
            if (!isNetworkAvailable(config.context)) Status.Disconnected.NetworkMissing
            else if (!checkInternetWithHttp204AndOkHttp()) Status.Disconnected.InternetMissing
            else Status.Connected
        _status.emit(result)
        Log.d("InternetMonitor", "checkStatus - $result")
        _status.emit(Status.Connected)
    }
}
