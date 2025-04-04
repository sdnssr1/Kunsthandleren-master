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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.collections.count
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.util.calculateTotalPrice


@Composable
fun PurchaseItemCard(
    modifier: Modifier = Modifier,
    purchaseItem: PurchaseItem,
    onDeleteClicked: (Long) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Artist Info
        Column(modifier = Modifier
            .weight(1f)
            .padding(4.dp)) {
            Text(text = purchaseItem.photo.artist.name)
            Text(text = purchaseItem.photo.artist.familyName)
        }
        // Frame Type and Photo Size
        Column(modifier = Modifier
            .weight(1f)
            .padding(4.dp)) {
            Text(text = "Frame: ${purchaseItem.frameType}")
            Text(text = "Photo: ${purchaseItem.size}")
        }
        // Sizes
        Column(modifier = Modifier
            .weight(1f)
            .padding(4.dp)) {
            Text(text = "Frame Width: ${purchaseItem.frameSize.size} dp")
            Text(text = "Photo Size: ${purchaseItem.size.size} dp")
        }
        // Delete button
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { onDeleteClicked(purchaseItem.photo.id) }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete purchase item"
            )
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    items: List<PurchaseItem>,
    onDeleteClicked: (Long) -> Unit,
    onNextButtonClicked: (Filters) -> Unit = {},
    onPurchaseClicked: () -> Unit = {}
) {
    val totalCost = calculateTotalPrice(items)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Velg bilde basert på",
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
                    containerColor = Color.Black,
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
                    containerColor = Color.Black,
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

        Text("Antall bilder valgt: ${items.count()}",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text("Totalpris: $totalCost",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onPurchaseClicked() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Buy Now",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = IBMVGAFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp


                )
            )
        }

    }
}
