package org.digital101.simplewallet.business.network.pingOne.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("username") val email: String,
    @SerialName("password") val password: String,
)
