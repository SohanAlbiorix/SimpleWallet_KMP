package org.digital101.simplewallet.common

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val versionCode: String =
        NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as? String
            ?: "Unknown"
}

actual fun getPlatform(context: Context): Platform = IOSPlatform()