package org.digital101.simplewallet.business.network.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MainGenericResponse<T>(
    @SerialName("data") var result: T?,
    @SerialName("status") var status: Status?,
    @SerialName("message") var alert: String?,
)

@Serializable
data class Status(
    val code: String,
    val message: String
)