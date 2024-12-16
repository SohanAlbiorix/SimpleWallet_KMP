package org.digital101.simplewallet.business.network.pingOne

import org.digital101.simplewallet.business.network.common.MainGenericResponse
import org.digital101.simplewallet.business.network.pingOne.responses.AuthorizeResponsesDTO
import org.digital101.simplewallet.business.network.pingOne.responses.LoginResponsesDTO
import org.digital101.simplewallet.business.network.pingOne.responses.ObtainTokenResponseDTO
import org.digital101.simplewallet.business.network.pingOne.responses.ResumeForTokenDTO

interface PingOneService {
    companion object {
        const val AUTHORIZE = "as/authorize"
        const val LOGIN = "flows"
        const val RESUME_FOR_TOKEN = "as/resume"
        const val OBTAIN_TOKEN = "as/token"
    }

    suspend fun authorize(): MainGenericResponse<AuthorizeResponsesDTO?>

    suspend fun login(
        email: String,
        password: String,
        flowId: String
    ): MainGenericResponse<LoginResponsesDTO?>

    suspend fun resumeForToken(flowId: String): MainGenericResponse<ResumeForTokenDTO?>

    suspend fun obtainToken(code: String): MainGenericResponse<ObtainTokenResponseDTO?>
}
