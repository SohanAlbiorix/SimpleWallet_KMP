package org.digital101.simplewallet.presentation.ui.main.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.digital101.simplewallet.presentation.ui.main.home.domain.LoanItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoanCard(
    modifier: Modifier = Modifier,
    loanItem: LoanItem,
) {
    Card(
        modifier = modifier
            .fillMaxWidth().padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(2f)) {
                Text(
                    text = loanItem.category,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                        shape = RoundedCornerShape(4.dp),
                    ).padding(paddingValues = PaddingValues(horizontal = 6.dp, vertical = 4.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = loanItem.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = loanItem.description,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 2.dp)
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
