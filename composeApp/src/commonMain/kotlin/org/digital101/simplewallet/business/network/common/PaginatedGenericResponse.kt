package org.digital101.simplewallet.business.network.common

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedGenericResponse<T>(
    val data: List<T>?,
    val paging: Paging?,
    val status: Status?,
    val message: String?,
)

@Serializable
data class Paging(
    val totalRecords: Int?,
    val pageSize: Int?,
    val pageNumber: Int?,
)