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

object KtorHttpClient {
    fun httpClient() = HttpClient {
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
                /*if (statusCode == 401) {
                    tokenManager.onTriggerEvent(TokenEvent.Logout)
                }*/


                /*
                                    when (statusCode) {
                                        in 300..399 -> throw RedirectResponseException(response)
                                        in 400..499 -> throw ClientRequestException(response)
                                        in 500..599 -> throw ServerResponseException(response)
                                    }

                                    if (statusCode >= 600) {
                                        throw ResponseException(response)
                                    }
                                }

                                handleResponseException { cause: Throwable ->
                                    throw cause
                                }*/
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
