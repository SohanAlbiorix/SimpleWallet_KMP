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

    suspend fun authorize(): AuthorizeResponsesDTO?

    suspend fun login(
        email: String,
        password: String,
        flowId: String
    ): LoginResponsesDTO?

    suspend fun resumeForToken(flowId: String): ResumeForTokenDTO?

    suspend fun obtainToken(code: String): ObtainTokenResponseDTO?
}
