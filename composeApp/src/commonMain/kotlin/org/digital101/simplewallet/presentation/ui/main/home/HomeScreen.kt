package org.digital101.simplewallet.presentation.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.theme.BaseColors
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.app_logo
import simplewallet.composeapp.generated.resources.arrow_right
import simplewallet.composeapp.generated.resources.bill
import simplewallet.composeapp.generated.resources.icon
import simplewallet.composeapp.generated.resources.label_activate_your_virtual_card_now
import simplewallet.composeapp.generated.resources.profile

@Composable
fun HomeScreen(
    profileViewModel: ProfileViewModel,
    onHamburgerClick: () -> Unit,
) {
    DefaultScreenUI(
        isHamburgerMenu = true,
        onHamburgerClick = onHamburgerClick,
        userName = profileViewModel.state.value.preferredUsername,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
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

            CreditCardView()

            /*AtmCard()*/

            transferList()

            LoanCardList()
        }
    }
}


@Composable
fun CreditCardView(
    cardholderName: String = "JOHN DOE",
    cardType: String = "Total Balance",
    bankName: String = "My Bank",
    totalBalance: String = "$5,432.15"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(top = 16.dp, bottom = 16.dp)
            .background(
                color = Color(0xFF2C3E50), // Card background color
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Bank Logo and Name
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Bank Logo (Use your image resource here)
                Image(
                    painter = painterResource(Res.drawable.app_logo),
                    contentDescription = "Bank Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                )
                // Bank Name
                Text(
                    text = bankName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Card Type (VISA, MasterCard, etc.)
            Text(
                text = cardType,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Card Number

            Spacer(modifier = Modifier.height(12.dp))
            // Expiry Date and Cardholder Name
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Cardholder Name
                Text(
                    text = cardholderName,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Total Balance
            Text(
                text = "Balance: $totalBalance",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun AtmCard() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(2) { index ->
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(350.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)
            ) {
                // Row to contain the image on the left and text at the center
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart), // Aligning to the top left of the card
                    verticalAlignment = Alignment.Top
                ) {
                    /*Image(
                        painter = painterResource(),
                        contentDescription = "ATM Card Image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                    )*/
                }
                Text(
                    text = "ATM Card ${index + 1}",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center) // Text will be centered
                )
            }
        }
    }
}


@Composable
fun transferList() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(3) { index ->  // Specify the number of items to show (3 in this case)
            Box(
                modifier = Modifier
                    .fillParentMaxWidth(1f / 3)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(Res.drawable.bill),
                        contentDescription = "Bank Logo",
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${index + 1}",
                        color = Color.Black,
                    )
                }
            }
        }
    }
}


@Composable
fun LoanCardList() {
    val loanItems = listOf(
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

    LazyColumn(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
        items(loanItems) { loanItem ->
            Spacer(modifier = Modifier.height(8.dp))
            LoanCard(loanItem)
        }
    }
}

@Composable
fun LoanCard(loanItem: LoanItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(2f)) {

                Text(
                    text = loanItem.category,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = loanItem.title,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = loanItem.description,
                )

            }

            Image(
                painter = painterResource(loanItem.imageRes),
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

data class LoanItem(
    val category: String,
    val title: String,
    val description: String,
    val imageRes: DrawableResource
)
