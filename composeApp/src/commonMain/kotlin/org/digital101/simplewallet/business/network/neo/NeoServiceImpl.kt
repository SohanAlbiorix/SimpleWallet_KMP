package org.digital101.simplewallet.business.network.neo

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import org.digital101.simplewallet.business.constants.NEO_BANK_BASE_URL
import org.digital101.simplewallet.business.network.common.MainGenericResponse
import org.digital101.simplewallet.business.network.neo.NeoService.Companion.ME
import org.digital101.simplewallet.business.network.neo.NeoService.Companion.MEMBERSHIP
import org.digital101.simplewallet.business.network.neo.responses.UserDataDTO

class NeoServiceImpl(
    private val httpClient: HttpClient
) : NeoService {
    override suspend fun user(token: String): MainGenericResponse<UserDataDTO?> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(NEO_BANK_BASE_URL)
                encodedPath += ME
            }
        }.body()
    }

    override suspend fun updateUser(
        token: String,
        userId: String,
        data: UserDataDTO
    ): MainGenericResponse<UserDataDTO?> {
        return httpClient.patch {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(NEO_BANK_BASE_URL)
                encodedPath += "$MEMBERSHIP/$userId"
            }
            contentType(ContentType.Application.Json)
            setBody(data)
        }.body()
    }
}