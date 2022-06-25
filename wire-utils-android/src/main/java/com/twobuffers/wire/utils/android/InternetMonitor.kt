package com.twobuffers.wire.utils.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.twobuffers.wire.coroutines.everyFlow
import java.net.InetAddress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

fun isNetworkAvailable(ctx: Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager? ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val activeNetwork = cm.activeNetwork
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

fun isInternetAvailable(hostname: String): Boolean =
    try {
        val ipAddr = InetAddress.getByName(hostname)
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }

object InternetMonitor {
    data class Config(
        val host: String = "google.com",
        val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
        val scope: CoroutineScope =
    )

    lateinit var config: Config

    private val _connected = MutableSharedFlow<Boolean>()
    val connected: SharedFlow<Boolean> = _connected

    fun init(config: Config) {
        this.config = config
    }
}
