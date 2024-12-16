package org.digital101.simplewallet.business.network.neo.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val data: UserData,
    val status: Status
)

@Serializable
data class UserData(
    val userId: String,
    val userName: String,
    val title: String,
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val nickName: String?,
    val fullName: String,
    val gender: String,
    val placeOfBirth: String,
    val countryOfBirth: String,
    val maritalStatus: String,
    val dateOfBirth: String,
    val email: String,
    val mobileNumber: String,
    val isCitizen: Boolean,
    val isUSCitizen: Boolean,
    val nationality: String,
    val status: String,
    val userType: String,
    val lastLoginAt: String,
    val isPresentAsPermAddress: Boolean,
    val taxDetails: List<String>,
    val listRoles: List<String>,
    val permissions: List<String>,
    val segments: List<String>,
    val createdAt: String,
    val passwordExpired: Boolean,
    val updatedAt: String,
    val religion: String,
    val ethnicity: String,
    val cif: String,
    val highestEducation: String,
    val residentialOwnershipStatus: String,
)

@Serializable
data class Status(
    val code: String,
    val message: String
)