package org.digital101.simplewallet.presentation.ui.main.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.common.getPlatform
import org.digital101.simplewallet.presentation.navigation.MainNavigation
import org.digital101.simplewallet.presentation.ui.main.settings.model.ProfileModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.arrow
import simplewallet.composeapp.generated.resources.label_101_digital_pte_ltd
import simplewallet.composeapp.generated.resources.label_account_settings
import simplewallet.composeapp.generated.resources.label_change_language
import simplewallet.composeapp.generated.resources.label_log_out
import simplewallet.composeapp.generated.resources.label_marketing_preferences
import simplewallet.composeapp.generated.resources.label_my_profile
import simplewallet.composeapp.generated.resources.label_payments_and_transfer
import simplewallet.composeapp.generated.resources.label_security
import simplewallet.composeapp.generated.resources.label_support_center
import simplewallet.composeapp.generated.resources.label_version
import simplewallet.composeapp.generated.resources.language
import simplewallet.composeapp.generated.resources.market
import simplewallet.composeapp.generated.resources.payment
import simplewallet.composeapp.generated.resources.profile
import simplewallet.composeapp.generated.resources.security
import simplewallet.composeapp.generated.resources.support

@Composable
fun AccountSettings(
    context: Context,
    navBottomBarController: NavHostController,
    backClick: () -> Unit
) {
    val profileItems = listOf(
        ProfileModel(Res.drawable.profile, stringResource(Res.string.label_my_profile), onClick = {
            navBottomBarController.navigate(MainNavigation.Profile)
        }),
        ProfileModel(Res.drawable.payment, stringResource(Res.string.label_payments_and_transfer)),
        ProfileModel(Res.drawable.security, stringResource(Res.string.label_security)),
        ProfileModel(Res.drawable.market, stringResource(Res.string.label_marketing_preferences)),
        ProfileModel(Res.drawable.support, stringResource(Res.string.label_support_center)),
        ProfileModel(Res.drawable.language, stringResource(Res.string.label_change_language))
    )

    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IconButton(
                onClick = backClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                )
            }
            Text(
                text = stringResource(Res.string.label_account_settings),
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp, top = 16.dp)
            )

            HorizontalDivider(
                color = Color.LightGray, modifier = Modifier.padding(
                    horizontal = 16.dp,
                )
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                items(profileItems) { profileItem ->
                    ProfileItemView(profileItem = profileItem)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 24.dp)
                        .clickable { /* Handle logout action */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = stringResource(Res.string.label_log_out),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        Res.string.label_version,
                        getPlatform(context).versionCode
                    ),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(Res.string.label_101_digital_pte_ltd),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )
            }
        }
    }
}

@Composable
fun ProfileItemView(profileItem: ProfileModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                profileItem.onClick?.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(profileItem.icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = profileItem.title,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(Res.drawable.arrow),
            contentDescription = null,
        )
    }
    HorizontalDivider(
        color = Color.LightGray, modifier = Modifier.padding(
            horizontal = 16.dp
        )
    )
}
