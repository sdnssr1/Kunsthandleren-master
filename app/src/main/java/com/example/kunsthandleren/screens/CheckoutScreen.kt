package com.example.kunsthandleren.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.util.calculateTotalPrice

@Composable
fun CheckoutScreen(
    items: List<PurchaseItem>,
    navController: NavController,
    onResetCart: () -> Unit
) {
    val context = LocalContext.current
    val total = calculateTotalPrice(items)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = "${stringResource(id = R.string.total_price)}: $total",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White, // Set the text color here
            modifier = Modifier.padding(bottom = 4.dp)
        )


        Button(
            onClick = {
                Toast.makeText(context, "Thanks for your purchase!", Toast.LENGTH_SHORT).show()
                onResetCart()
                navController.navigate("home") {
                    popUpTo("checkout") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = stringResource(id = R.string.pay))
        }
    }

}
@Composable
fun PreviewPurchaseScreen() {
    CheckoutScreen(
        items = listOf(PurchaseItem(DataSource.naturePhotos[0])),
        navController = rememberNavController(),
        onResetCart = {}
    )
}