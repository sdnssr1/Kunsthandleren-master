package com.example.kunsthandleren.screens

import IBMVGAFontFamily
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.util.calculateTotalPrice

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    items: List<PurchaseItem>,
    onNextButtonClicked: (Filters) -> Unit = {},
    onPurchaseClicked: () -> Unit = {}
) {
    val totalCost = calculateTotalPrice(items)
    val darkGreen = Color(0xFF006400)


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Velg rammer basert på",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp

        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onNextButtonClicked(Filters.ARTIST) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Kunstner",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = IBMVGAFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }
            Button(
                onClick = { onNextButtonClicked(Filters.CATEGORY) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Kategori",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = IBMVGAFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    )) {
                    append("Antall rammer valgt: ")
                }
                withStyle (SpanStyle(color = Color.Red, fontSize = 24.sp))
                {
                    append("${items.count()}")
                }
                withStyle (SpanStyle(color = Black, fontSize = 24.sp))
                {
                    append(" Ramme(r)")
                }
            },
        )
        Text(
            buildAnnotatedString {
                // Bold label for "Total pris: "
                withStyle(SpanStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )) {
                    append("Total pris: ")
                }
                withStyle(SpanStyle(
                    color = darkGreen,
                    fontSize = 24.sp
                )) {
                    append("%.0f".format(totalCost))
                }
                withStyle(SpanStyle(
                        color = Black,
                    fontSize = 24.sp
                )) {
                    append(" NOK")
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onPurchaseClicked() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Betal Nå!",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = IBMVGAFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
        }

    }
}
