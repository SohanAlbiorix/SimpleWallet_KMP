package org.digital101.simplewallet.presentation.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.app_logo

@Composable
fun CreditCardView(
    modifier: Modifier = Modifier,
    totalBalance: String = "$5,432.15"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 16.dp, bottom = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.tertiary, // Card background color
                shape = RoundedCornerShape(16.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
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
                    modifier = Modifier.align(Alignment.CenterVertically).height(50.dp)
                )
            }

            Column {
                Text(
                    text = "Total Balance",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Balance: $totalBalance",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}