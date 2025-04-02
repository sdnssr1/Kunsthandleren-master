package com.example.kunsthandleren.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.collections.count
import androidx.compose.ui.res.colorResource
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
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = purchaseItem.photo.artist.name.toString())
            Text(text = purchaseItem.photo.artist.familyName.toString())
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(purchaseItem.frameType.title))
            Text(text = stringResource(purchaseItem.size.title))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = purchaseItem.frameSize.size.toString())
            Text(text = purchaseItem.size.size.toString())
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { onDeleteClicked(purchaseItem.photo.id) }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete purchase item")
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
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onNextButtonClicked(Filters.ARTIST) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = colorResource(id = R.color.white)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text("Kunstner")
            }

            Button(
                onClick = { onNextButtonClicked(Filters.CATEGORY) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = colorResource(id = R.color.white)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text("Kategori")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Antall bilder valgt: ${items.count()}")
        Text("Totalpris: $totalCost")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onPurchaseClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.teal_700),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text("Til betaling")
        }
    }
}
