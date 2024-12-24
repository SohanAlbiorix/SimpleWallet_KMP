package org.digital101.simplewallet.presentation.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.theme.BaseColors
import org.digital101.simplewallet.presentation.ui.main.home.components.CreditCardView
import org.digital101.simplewallet.presentation.ui.main.home.components.ItemWithImage
import org.digital101.simplewallet.presentation.ui.main.home.components.LoanCard
import org.digital101.simplewallet.presentation.ui.main.home.domain.LoanItem
import org.digital101.simplewallet.presentation.ui.main.home.utils.Items
import org.digital101.simplewallet.presentation.ui.main.home.viewModel.HomeViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.arrow_right
import simplewallet.composeapp.generated.resources.icon
import simplewallet.composeapp.generated.resources.label_activate_your_virtual_card_now

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
    onHamburgerClick: () -> Unit,
) {
    DefaultScreenUI(
        isHamburgerMenu = true,
        onHamburgerClick = onHamburgerClick,
        userName = viewModel.state.value.data?.userName,
        progressBarState = viewModel.state.value.progressBarState,
        networkState = viewModel.state.value.networkState,
        queue = viewModel.state.value.errorQueue,
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 14.dp).fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    border = BorderStroke(color = BaseColors.BorderColor, width = 1.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.icon),
                            contentDescription = null,
                        )
                        Text(
                            text = stringResource(Res.string.label_activate_your_virtual_card_now),
                            modifier = Modifier.padding(start = 12.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = painterResource(Res.drawable.arrow_right),
                            contentDescription = null,
                        )
                    }
                }
            }


            item {
                CreditCardView(
                    totalBalance = (viewModel.state.value.wallet?.availableBalance ?: 0).toString()
                )
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(Items.transactions.size) { index ->
                        ItemWithImage(
                            modifier = Modifier.fillParentMaxWidth(fraction = (1f / 3.15).toFloat()),
                            transactionItem = Items.transactions[index]
                        )
                    }
                }
            }

            items(Items.loans) { loanItem ->
                Spacer(modifier = Modifier.height(8.dp))
                LoanCard(loanItem = loanItem)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}