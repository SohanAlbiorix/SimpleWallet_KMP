package org.digital101.simplewallet.presentation.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.presentation.ui.main.home.domain.TransactionItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemWithImage(
    modifier: Modifier = Modifier,
    transactionItem: TransactionItem
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .aspectRatio(1F)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image (drawable)
            Image(
                painter = painterResource(transactionItem.imageRes),
                contentDescription = transactionItem.name,
                modifier = Modifier.height(50.dp).width(50.dp)
            )
            // Text
            Text(
                text = transactionItem.name,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}