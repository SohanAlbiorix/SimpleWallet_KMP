package org.digital101.simplewallet.di

import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.AppDataStoreManager
import org.digital101.simplewallet.business.core.KtorHttpClient
import org.digital101.simplewallet.business.datasource.network.auth.AuthService
import org.digital101.simplewallet.business.datasource.network.auth.AuthServiceImpl
import org.digital101.simplewallet.business.interactors.auth.AuthInteractor
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.navigation.NavigationGraphViewModel
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun dataModule(context: Context) = module {
    single { KtorHttpClient.httpClient() }

    single<AuthService> { AuthServiceImpl(get()) }
    single<AppDataStore> { AppDataStoreManager(context) }
    single { AuthInteractor(get(), get()) }
}

val viewModelModule = module {
    factoryOf(::NavigationGraphViewModel)
    factory {
        LoginViewModel(get())
    }
}
