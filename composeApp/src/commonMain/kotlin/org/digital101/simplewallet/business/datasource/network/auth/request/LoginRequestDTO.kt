package org.digital101.simplewallet.business.datasource.network.auth.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
