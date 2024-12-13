package org.digital101.simplewallet.business.core

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.digital101.simplewallet.presentation.token_manager.TokenEvent
import org.digital101.simplewallet.presentation.token_manager.TokenManager

object KtorHttpClient {
    fun httpClient(tokenManager: TokenManager) = HttpClient {
        followRedirects = false
        expectSuccess = false
        install(HttpTimeout) {
            val timeout = 60000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(HttpCookies) {
            storage = AcceptAllCookiesStorage() // No cookies are stored
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response.status.value}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value
                if (statusCode == 401) {
                    tokenManager.onTriggerEvent(TokenEvent.Logout)
                }
            }
        }

        install(Logging) {
            level = LogLevel.ALL

            logger = object : Logger {
                override fun log(message: String) {
                    println("AppDebug ---------------------- message:$message")
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                encodeDefaults = true
                classDiscriminator = "#class"
            }, contentType = ContentType.Any)
        }
    }
}
