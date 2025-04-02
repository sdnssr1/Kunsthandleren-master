package com.example.kunsthandleren.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.util.calculateTotalPrice


@Composable
fun CheckoutScreen(items: List<PurchaseItem>) {
    val context = LocalContext.current
    val unimplementedMsg = stringResource(id = R.string.unimplemented)
    val infoToast = Toast.makeText(context, unimplementedMsg, Toast.LENGTH_SHORT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.total_price),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        val total = calculateTotalPrice(items)
        Text(
            text = "$total",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = { infoToast.show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.teal_700),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = stringResource(id = R.string.pay))
        }

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewPurchaseScreen() {
    CheckoutScreen(listOf(PurchaseItem(DataSource.photos[0])))
}