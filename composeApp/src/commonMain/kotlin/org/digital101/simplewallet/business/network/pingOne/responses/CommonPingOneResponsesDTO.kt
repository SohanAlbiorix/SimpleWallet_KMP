package org.digital101.simplewallet.business.network.pingOne.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Embedded(
    val user: User?,
    val application: Application?,
)

@Serializable
data class Application(
    val name: String?
)

@Serializable
data class Links(
    @SerialName("password.forgot") val forgot: Link?,
    val self: Link?,
    @SerialName("signOnPage") val signOnPage: Link?,
    @SerialName("usernamePassword.check") val check: Link?,
)

@Serializable
data class Environment(
    @SerialName("id") val id: String?
)

@Serializable
data class Link(
    @SerialName("href") val href: String?
)

@Serializable
data class User(
    val id: String?,
    val username: String?,
)