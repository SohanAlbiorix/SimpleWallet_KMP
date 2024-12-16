package org.digital101.simplewallet.business.network.pingOne.request

import kotlinx.serialization.Serializable

@Serializable
data class ObtainTokenRequestDTO(
    val scope: String,
    val code_verifier: String,
    val client_id: String,
    val grant_type: String,
    val code: String,

)
