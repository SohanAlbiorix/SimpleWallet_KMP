package org.digital101.simplewallet.di

import kotlinx.serialization.json.Json
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.AppDataStoreManager
import org.digital101.simplewallet.business.core.KtorHttpClient
import org.digital101.simplewallet.business.interactors.auth.AuthInteract
import org.digital101.simplewallet.business.interactors.auth.CheckTokenInteract
import org.digital101.simplewallet.business.interactors.auth.LogoutInteract
import org.digital101.simplewallet.business.interactors.neobank.UpdateProfileInteract
import org.digital101.simplewallet.business.interactors.neobank.UserInteract
import org.digital101.simplewallet.business.interactors.neobank.WalletInteract
import org.digital101.simplewallet.business.network.neo.NeoService
import org.digital101.simplewallet.business.network.neo.NeoServiceImpl
import org.digital101.simplewallet.business.network.pingOne.PingOneService
import org.digital101.simplewallet.business.network.pingOne.PingOneServiceImpl
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.SharedViewModel
import org.digital101.simplewallet.presentation.tokenManager.TokenManager
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginViewModel
import org.digital101.simplewallet.presentation.ui.main.home.viewModel.HomeViewModel
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.koin.dsl.module

fun dataModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single { KtorHttpClient.httpClient(get()) }

    single<PingOneService> { PingOneServiceImpl(get()) }
    single<NeoService> { NeoServiceImpl(get()) }

    factory { SharedViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ProfileViewModel(get(), get()) }
    factory { HomeViewModel(get(), get()) }

    single { AuthInteract(get(), get()) }
    single { CheckTokenInteract(get()) }
    single { LogoutInteract(get()) }
    single { UserInteract(get(), get()) }
    single { UpdateProfileInteract(get(), get()) }
    single { WalletInteract(get(), get()) }

    single<AppDataStore> { AppDataStoreManager(context) }
    single { TokenManager(get(), get()) }
}