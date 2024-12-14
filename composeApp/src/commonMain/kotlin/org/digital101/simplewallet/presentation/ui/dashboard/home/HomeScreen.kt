package org.digital101.simplewallet.presentation.ui.dashboard.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.theme.BaseColors
import org.digital101.simplewallet.presentation.ui.dashboard.settings.AccountSettings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.arrow_right
import simplewallet.composeapp.generated.resources.icon
import simplewallet.composeapp.generated.resources.label_activate_your_virtual_card_now

@Composable
fun HomeScreen(navBottomBarController: NavHostController, context: Context) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AccountSettings(context, navBottomBarController) {
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        }
    ) {
        DefaultScreenUI(
            userName = "",
            onClickStartIconToolbar = {
                if (drawerState.isClosed) {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
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

                AtmCard()
            }
        }
    }
}

@Composable
fun AtmCard() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(400.dp)// Fixed height
                    .background(Color.Gray)
            ) {
                Text(
                    text = "ATM Card",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}