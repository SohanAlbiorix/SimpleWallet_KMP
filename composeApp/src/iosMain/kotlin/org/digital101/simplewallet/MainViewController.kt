package org.digital101.simplewallet

import androidx.compose.ui.window.ComposeUIViewController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.App

fun MainViewController() = ComposeUIViewController { App(Context()) }