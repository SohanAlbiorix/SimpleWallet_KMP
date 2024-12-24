package org.digital101.simplewallet.presentation.ui.main.home.utils

import org.digital101.simplewallet.presentation.ui.main.home.domain.LoanItem
import org.digital101.simplewallet.presentation.ui.main.home.domain.TransactionItem
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.action_1
import simplewallet.composeapp.generated.resources.action_2
import simplewallet.composeapp.generated.resources.action_3
import simplewallet.composeapp.generated.resources.profile

object Items {
    val transactions = arrayListOf(
        TransactionItem("Transfer", Res.drawable.action_1),
        TransactionItem("Reward", Res.drawable.action_2),
        TransactionItem("Bills", Res.drawable.action_3),
    )

    val loans = listOf(
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
    )
}