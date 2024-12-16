package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizeResponsesDTO(
    @SerialName("_embedded") val _embedded: Embedded,
    @SerialName("_links") val _links: Links,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("environment") val environment: Environment,
    @SerialName("expiresAt") val expiresAt: String,
    @SerialName("id") val id: String,
    @SerialName("resumeUrl") val resumeUrl: String,
    @SerialName("status") val status: String
)

@Serializable
data class Embedded(
    @SerialName("application") val application: Application
)

@Serializable
data class Application(
    @SerialName("name") val name: String
)

@Serializable
data class Links(
    @SerialName("password.forgot") val forgot: PasswordForgot,
    @SerialName("self") val self: Self,
    @SerialName("signOnPage") val signOnPage: SignOnPage,
    @SerialName("usernamePassword.check") val check: UsernamePasswordCheck
)

@Serializable
data class Environment(
    @SerialName("id") val id: String
)

@Serializable
data class PasswordForgot(
    @SerialName("href") val href: String
)

@Serializable
data class Self(
    @SerialName("href") val href: String
)

@Serializable
data class SignOnPage(
    @SerialName("href") val href: String
)

@Serializable
data class UsernamePasswordCheck(
    @SerialName("href") val href: String
)