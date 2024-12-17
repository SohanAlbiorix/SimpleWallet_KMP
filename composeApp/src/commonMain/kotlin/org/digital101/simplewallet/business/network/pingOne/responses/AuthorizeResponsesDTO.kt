package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizeResponsesDTO(
    @SerialName("_embedded") val embedded: Embedded?,
    @SerialName("_links") val links: Links?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("environment") val environment: Environment?,
    @SerialName("expiresAt") val expiresAt: String?,
    @SerialName("id") val id: String?,
    @SerialName("resumeUrl") val resumeUrl: String?,
    @SerialName("status") val status: String?
)