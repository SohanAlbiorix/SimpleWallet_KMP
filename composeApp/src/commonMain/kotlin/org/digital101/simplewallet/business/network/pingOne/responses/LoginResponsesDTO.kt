package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponsesDTO(
    @SerialName("_links") val links: Links,
    val id: String,
    val session: Session,
    val resumeUrl: String,
    val status: String,
    val createdAt: String,
    val expiresAt: String,
    @SerialName("_embedded") val embedded: Embedded,
)

@Serializable
data class Session(
    val id: String?
)

