package org.digital101.simplewallet.business.datasource.network.auth.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeForTokenDTO(
    @SerialName("_links")
    val links: ResumeForTokenLinks,
    @SerialName("_embedded")
    val embedded: ResumeForTokenEmbedded,
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
data class ResumeForTokenLinks(
    val self: Self,
    val signOnPage: SignOnPage
)

@Serializable
data class ResumeForTokenEmbedded(
    val user: User,
    val application: Application
)

@Serializable
data class AuthorizeResponse(
    val code: String
)