package org.digital101.simplewallet.business.network.pingOne

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
import org.digital101.simplewallet.business.constants.PING_ONE_BASE_URL
import org.digital101.simplewallet.business.network.pingOne.request.LoginRequestDTO
import org.digital101.simplewallet.business.network.pingOne.responses.AuthorizeResponsesDTO
import org.digital101.simplewallet.business.network.pingOne.responses.LoginResponsesDTO
import org.digital101.simplewallet.business.network.pingOne.responses.ObtainTokenResponseDTO
import org.digital101.simplewallet.business.network.pingOne.responses.ResumeForTokenDTO
import org.digital101.simplewallet.business.network.common.MainGenericResponse

class PingOneServiceImpl(
    private val httpClient: HttpClient
) : PingOneService {

    override suspend fun authorize(): MainGenericResponse<AuthorizeResponsesDTO?> {
        val response = httpClient.get {
            url {
                takeFrom(PING_ONE_BASE_URL)
                encodedPath += "${API_KEY}/${PingOneService.AUTHORIZE}"
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
                takeFrom(PING_ONE_BASE_URL)
                encodedPath += "${API_KEY}/${PingOneService.LOGIN}/$flowId"
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
                takeFrom(PING_ONE_BASE_URL)
                encodedPath += "${API_KEY}/${PingOneService.RESUME_FOR_TOKEN}"
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
                    takeFrom(PING_ONE_BASE_URL)
                    encodedPath += "${API_KEY}/${PingOneService.OBTAIN_TOKEN}"
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