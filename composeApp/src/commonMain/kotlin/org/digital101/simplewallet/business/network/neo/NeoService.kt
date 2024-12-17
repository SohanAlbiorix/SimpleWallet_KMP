package org.digital101.simplewallet.business.network.neo

import org.digital101.simplewallet.business.network.common.MainGenericResponse
import org.digital101.simplewallet.business.network.common.PaginatedGenericResponse
import org.digital101.simplewallet.business.network.neo.responses.UserDataDTO
import org.digital101.simplewallet.business.network.neo.responses.WalletResponseDTO

interface NeoService {
    companion object {
        const val MEMBERSHIP = "membership-service/1.0.0/users"
        const val ME = "$MEMBERSHIP/me"
        const val WALLET = "wallet-service/1.0.0/wallets"
    }

    suspend fun user(token: String): MainGenericResponse<UserDataDTO?>

    suspend fun updateUser(
        token: String,
        userId: String,
        data: UserDataDTO,
    ): MainGenericResponse<UserDataDTO?>

    suspend fun wallet(token: String) : PaginatedGenericResponse<WalletResponseDTO>
}