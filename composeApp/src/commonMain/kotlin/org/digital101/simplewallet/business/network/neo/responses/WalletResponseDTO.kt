package org.digital101.simplewallet.business.network.neo.responses

import kotlinx.serialization.Serializable

@Serializable
data class WalletResponseDTO(
    val createdAt: String?,
    val internalId: String?,
    val walletId: String?,
    val userId: String?,
    val walletName: String?,
    val currentBalance: Double?,
    val availableBalance: Double?,
    val minimumBalance: Double?,
    val bankAccount: BankAccount?,
    val currencyCode: String?,
    val status: String?,
    val type: String?,
    val isDefaultWallet: Boolean?,
    val updatedAt: String?,
    val createdBy: String?,
    val entityId: String?,
    val appId: String?
)

@Serializable
data class BankAccount(
    val account: List<Account>?,
    val openingDate: String?,
    val applicationId: String?,
    val balance: List<Balance>?,
    val customerId: String?,
    val bankCode: String?,
    val accountType: String?,
    val accountNumber: String?,
    val bankCustomerId: String?,
    val accountHolderName: String?,
    val internalProductCategory: String?,
    val productId: String?,
    val countryCode: String?,
    val accountId: String?,
    val accountSubType: String?,
    val id: String?
)

@Serializable
data class Account(
    val identification: String?,
    val schemeName: String?
)

@Serializable
data class Balance(
    val accountId: String?,
    val amount: Amount?,
    val type: String?
)

@Serializable
data class Amount(
    val amount: Double?,
    val currency: String?
)
