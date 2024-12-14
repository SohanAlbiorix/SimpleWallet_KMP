package org.digital101.simplewallet.common

import android.content.pm.PackageInfo
import android.os.Build

class AndroidPlatform(context: Context) : Platform {
    private val packageInfo: PackageInfo =
        context.packageManager.getPackageInfo(context.packageName, 0)
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val versionCode: String = packageInfo.versionName
}

actual fun getPlatform(context: Context): Platform = AndroidPlatform(context)
