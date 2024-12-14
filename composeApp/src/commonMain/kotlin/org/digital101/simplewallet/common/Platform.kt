package org.digital101.simplewallet.common

interface Platform {
    val name: String
    val versionCode: String
}

expect fun getPlatform(context: Context): Platform