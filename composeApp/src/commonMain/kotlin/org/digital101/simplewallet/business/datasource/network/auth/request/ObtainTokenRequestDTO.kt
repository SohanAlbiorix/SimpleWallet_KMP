package org.digital101.simplewallet.business.datasource.network.auth.request

import kotlinx.serialization.Serializable

@Serializable
data class ObtainTokenRequestDTO(
    val scope: String,
    val client_id: String,
    val acr_values: String,
    val response_mode: String,
    val response_type: String
)
