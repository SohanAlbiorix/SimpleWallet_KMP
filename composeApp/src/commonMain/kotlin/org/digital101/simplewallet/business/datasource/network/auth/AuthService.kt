package org.digital101.simplewallet.business.datasource.network.auth

import org.digital101.simplewallet.business.datasource.network.auth.responses.AuthorizeResponsesDTO
import org.digital101.simplewallet.business.datasource.network.common.MainGenericResponse

interface AuthService {
    companion object {
        const val AUTHORIZE = "as/authorize"
        const val LOGIN = "flows"
    }

    suspend fun authorize(): MainGenericResponse<AuthorizeResponsesDTO?>
    suspend fun login(email: String, password: String): MainGenericResponse<String?>
}
