package org.digital101.simplewallet.di

import kotlinx.serialization.json.Json
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.AppDataStoreManager
import org.digital101.simplewallet.business.core.KtorHttpClient
import org.digital101.simplewallet.business.datasource.network.auth.AuthService
import org.digital101.simplewallet.business.datasource.network.auth.AuthServiceImpl
import org.digital101.simplewallet.business.interactors.auth.AuthInteract
import org.digital101.simplewallet.business.interactors.auth.CheckTokenInteract
import org.digital101.simplewallet.business.interactors.neobank.LogoutInteract
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.SharedViewModel
import org.digital101.simplewallet.presentation.tokenManager.TokenManager
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginViewModel
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.koin.dsl.module

fun dataModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single { KtorHttpClient.httpClient(get()) }

    single<AuthService> { AuthServiceImpl(get()) }
    single<AppDataStore> { AppDataStoreManager(context) }

    factory { SharedViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ProfileViewModel() }

    single { AuthInteract(get(), get()) }
    single { CheckTokenInteract(get()) }
    single { LogoutInteract(get()) }
    single { TokenManager(get(), get()) }
}