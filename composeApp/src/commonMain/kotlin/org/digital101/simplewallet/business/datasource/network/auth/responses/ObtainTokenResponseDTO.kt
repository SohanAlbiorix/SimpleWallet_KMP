package org.digital101.simplewallet.business.datasource.network.auth.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObtainTokenResponseDTO(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    val scope: String,
    @SerialName("id_token")
    val idToken: String
)