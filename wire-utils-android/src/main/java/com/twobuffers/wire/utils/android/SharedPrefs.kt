package com.twobuffers.wire.utils.android

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.io.File

internal fun getEncryptedSharedPrefs(ctx: Context, fileName: String): SharedPreferences {
    val mainKey = MasterKey.Builder(ctx).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    return EncryptedSharedPreferences.create(
        ctx,
        fileName,
        mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}

fun Context.getSharedPrefs(filename: String, encrypted: Boolean = false): SharedPreferences =
    if (encrypted) getEncryptedSharedPrefs(this, filename)
    else getSharedPreferences(filename, Context.MODE_PRIVATE)


@RequiresApi(VERSION_CODES.N)
fun Context.sharedPrefsExists(name: String): Boolean =
    File("${dataDir.absolutePath}/shared_prefs/${name}.xml").exists()
