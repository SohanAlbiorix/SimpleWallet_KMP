package org.digital101.simplewallet.business.network.neo

import org.digital101.simplewallet.business.network.common.MainGenericResponse
import org.digital101.simplewallet.business.network.neo.responses.UserData
import org.digital101.simplewallet.business.network.neo.responses.UserResponseDTO

interface NeoService {
    companion object {
        const val MEMBERSHIP = "membership-service/1.0.0/users"
        const val ME = "$MEMBERSHIP/me"
    }

    suspend fun user(token: String): MainGenericResponse<UserResponseDTO?>

    suspend fun updateUser(
        token: String,
        userId: String,
        data: UserData
    ): MainGenericResponse<UserResponseDTO?>
}