package org.digital101.simplewallet.business.datasource.network.auth

import org.digital101.simplewallet.business.datasource.network.auth.responses.AuthorizeResponsesDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.LoginResponsesDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.ObtainTokenResponseDTO
import org.digital101.simplewallet.business.datasource.network.auth.responses.ResumeForTokenDTO
import org.digital101.simplewallet.business.datasource.network.common.MainGenericResponse

interface AuthService {
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
