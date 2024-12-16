package org.digital101.simplewallet.business.util

import org.digital101.simplewallet.business.network.common.JAlertResponse


const val THROWABLE_DIVIDER = "THROWABLE_DIVIDER"

fun JAlertResponse.createException() =
    Throwable(" $THROWABLE_DIVIDER ${this.title} $THROWABLE_DIVIDER ${this.message}")