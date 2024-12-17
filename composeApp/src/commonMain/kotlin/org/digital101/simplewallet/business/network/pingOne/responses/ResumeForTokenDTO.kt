package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeForTokenDTO(
    @SerialName("_links") val links: Links,
    @SerialName("_embedded") val embedded: Embedded,
    val id: String,
    val environment: Environment,
    val session: Session,
    val resumeUrl: String,
    val status: String,
    val createdAt: String,
    val expiresAt: String,
    val authorizeResponse: AuthorizeResponse
)

@Serializable
data class AuthorizeResponse(
    val code: String
)