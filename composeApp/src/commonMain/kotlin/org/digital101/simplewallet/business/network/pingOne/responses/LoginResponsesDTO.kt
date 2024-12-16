package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponsesDTO(
    @SerialName("_links") val links: LoginLinks,
    val id: String,
    val session: Session,
    val resumeUrl: String,
    val status: String,
    val createdAt: String,
    val expiresAt: String,
    @SerialName("_embedded") val embedded: LoginEmbedded
)

@Serializable
data class LoginLinks(
    @SerialName("self") val self: Self,
)

@Serializable
data class Session(
    val id: String
)

@Serializable
data class LoginEmbedded(
    val user: User,
    val application: Application
)

@Serializable
data class User(
    val id: String,
    val username: String
)
