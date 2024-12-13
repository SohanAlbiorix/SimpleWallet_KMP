package org.digital101.simplewallet.business.datasource.network.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import org.digital101.simplewallet.BuildKonfig.API_KEY
import org.digital101.simplewallet.business.constants.BASE_URL_AUTH
import org.digital101.simplewallet.business.datasource.network.auth.request.LoginRequestDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.AuthorizeResponsesDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.LoginResponsesDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.ObtainTokenResponseDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.ResumeForTokenDTO
import org.digital101.simplewallet.business.datasource.network.common.MainGenericResponse

class AuthServiceImpl(
    private val httpClient: HttpClient
) : AuthService {


    override suspend fun authorize(): MainGenericResponse<AuthorizeResponsesDTO?> {
        val response = httpClient.get {
            url {
                takeFrom(BASE_URL_AUTH)
                encodedPath += "${API_KEY}/${AuthService.AUTHORIZE}"
                parameters.append("code_challenge_method", "S256")
                parameters.append(
                    "code_challenge",
                    "0ChgyTpVc9hkDJ3ag43oCPNpLanxTE111a_q4vYfUR8"
                )
                parameters.append("scope", "openid profilepsf")
                parameters.append("client_id", "80a1653d-b5e4-475a-9fe0-f287d81d8e49")
                parameters.append("acr_values", "Single_Factor")
                parameters.append("response_mode", "pi.flow")
                parameters.append("response_type", "code")
            }
            contentType(ContentType.Application.Json)
        }
        return MainGenericResponse(
            result = response.body(),
            status = true
        )
    }

    override suspend fun login(
        email: String,
        password: String,
        flowId: String
    ): MainGenericResponse<LoginResponsesDTO?> {
        val response = httpClient.post {
            url {
                takeFrom(BASE_URL_AUTH)
                encodedPath += "${API_KEY}/${AuthService.LOGIN}/$flowId"
            }
            contentType(
                ContentType(
                    "application",
                    "vnd.pingidentity.usernamePassword.check+json"
                )
            )
            setBody(LoginRequestDTO(email = email, password = password))
        }
        return MainGenericResponse(
            result = response.body(),
            status = true
        )
    }

    override suspend fun resumeForToken(flowId: String): MainGenericResponse<ResumeForTokenDTO?> {
        val response = httpClient.get {
            url {
                takeFrom(BASE_URL_AUTH)
                encodedPath += "${API_KEY}/${AuthService.RESUME_FOR_TOKEN}"
                parameters.append("flowId", flowId)
            }
        }
        return MainGenericResponse(
            result = response.body(),
            status = true
        )
    }

    override suspend fun obtainToken(code: String): MainGenericResponse<ObtainTokenResponseDTO?> {
        return MainGenericResponse(
            result = httpClient.post {
                url {
                    takeFrom(BASE_URL_AUTH)
                    encodedPath += "${API_KEY}/${AuthService.OBTAIN_TOKEN}"
                }
                contentType(ContentType.Application.Json)
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("scope", "openid profilepsf")
                            append(
                                "code_verifier",
                                "DC0m3unbBdWUn5cDTc5j22DixyQHgdrDJQAYBEwwtR2ybYMv04jf0E0feQ"
                            )
                            append("client_id", "80a1653d-b5e4-475a-9fe0-f287d81d8e49")
                            append("grant_type", "authorization_code")
                            append("code", code)
                        }
                    )
                )
            }.body(),
            status = true
        )
    }
}