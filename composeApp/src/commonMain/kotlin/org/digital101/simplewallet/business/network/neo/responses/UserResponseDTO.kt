package org.digital101.simplewallet.business.network.neo.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserDataDTO(
    val userId: String?,
    val userName: String?,
    val title: String?,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val nickName: String?,
    val fullName: String?,
    val gender: String?,
    val placeOfBirth: String?,
    val countryOfBirth: String?,
    val maritalStatus: String?,
    val dateOfBirth: String?,
    val email: String?,
    val mobileNumber: String?,
    val isCitizen: Boolean?,
    val isUSCitizen: Boolean?,
    val nationality: String?,
    val status: String?,
    val userType: String?,
    val lastLoginAt: String?,
    val isPresentAsPermAddress: Boolean?,
    val taxDetails: List<String>?,
    val listRoles: List<String>?,
    val permissions: List<String>?,
    val segments: List<String>?,
    val createdAt: String?,
    val passwordExpired: Boolean?,
    val updatedAt: String?,
    val religion: String?,
    val ethnicity: String?,
    val cif: String?,
    val highestEducation: String?,
    val residentialOwnershipStatus: String?,
    val contacts: List<Contact>?,
    val addresses: List<Address>?,
    val listCustomFields: List<CustomField>?,
    val employmentDetails: List<EmploymentDetail>?,
    val kycDetails: KycDetails?,
    val memberships: List<Membership>?,
)

@Serializable
data class Membership(
    val membershipId: String?,
    val organisationId: String?,
    val organisationName: String?,
    val roleName: String?,
    val token: String?,
    val organisationNumber: String?,
    val organisationStatus: String?,
    val companyNumber: String?,
    val leiNumber: String?,
    val tradingName: String?,
)

@Serializable
data class KycDetails(
    val verificationStatus: String?,
    val verificationDate: String?,
    val verificationBy: String?,
    val idType: String?,
    val idNumber: String?,
    val idIssuingCountry: String?,
    val idExpiredDate: String?,
    val altIdType: String?,
    val altIdNumber: String?,
    val altIdIssuingCountry: String?,
    val altIdExpiredDate: String?,
    val altDataFlag: Boolean?,
    val documents: List<String>?
)

@Serializable
data class Address(
    val id: String?,
    val addressType: String?,
    var line1: String?,
    var line2: String?,
    var line3: String?,
    var city: String?,
    var state: String?,
    var postcode: String?,
    var country: String?
)

@Serializable
data class CustomField(
    val customFieldId: String?,
    val customKey: String?,
    val customValue: String?
)

@Serializable
data class EmploymentDetail(
    val id: String?,
    val accountId: String?,
    var companyName: String?,
    var companyType: String?,
    var employmentType: String?,
    var occupation: String?,
    var sector: String?,
    var startDate: String?,
    var addresses: List<Address>?,
    var contactDetails: List<Contact>?
)

@Serializable
data class Contact(
    val id: String?,
    val contactType: String?,
    val contactValue: String?
)
