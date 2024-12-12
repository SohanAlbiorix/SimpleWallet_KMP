package org.digital101.simplewallet.common

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform