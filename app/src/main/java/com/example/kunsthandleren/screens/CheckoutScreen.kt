package com.example.kunsthandleren.screens

import IBMVGAFontFamily
import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.util.calculateTotalPrice
import com.example.kunsthandleren.viewmodel.ArtVendorScreen


@Composable
fun CheckoutScreen(
    items: List<PurchaseItem>,
    widthInCm: Float,
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
        // 🧾 Summary Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("Du har valgt ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("${items.size}")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append(" Ramme(r)")
                    }
                },
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("Total pris: ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF006400),
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("%.0f".format(total))
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Black
                        )
                    ) {
                        append(" NOK")
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        items.forEachIndexed { index, item ->
            item.photo.title
            "${item.photo.artist.name} ${item.photo.artist.familyName}"
            item.frameType.title
            "${item.size.size.toInt()} cm"

            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Black
                        )
                    ) {
                        append("${index + 1}. ") // 👈 Numbered counter
                        append("Product: ")
                    }

                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color(0xFF006400)
                        )
                    ) {
                        append(item.photo.title)
                        append("\n")

                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Black
                            )
                    ) {
                        append("   Image Width: ")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Blue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("%.1f ".format(widthInCm))
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily
                        )
                    ) {
                        append("cm²")
                        append("\n")

                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Black
                            )
                    ) {
                        append("   Material: ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Red
                            )
                    ) {
                        append(item.frameType.title)
                        append("\n")

                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.Black
                        )
                    ) {
                        append("   Artist: ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = IBMVGAFontFamily,
                            color = Color.DarkGray
                        )
                    ) {
                        append(item.photo.artist.name)
                        append(" ${item.photo.artist.familyName}")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    }
                },
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }


        // 🟦 Pay Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Klar til å betale?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = IBMVGAFontFamily,
                color = Color.Black
            )
        }

        Button(
            onClick = {
                Toast.makeText(context, "Thanks for your purchase!", Toast.LENGTH_SHORT).show()
                onResetCart()
                navController.navigate(ArtVendorScreen.Start.name) {
                    popUpTo("checkout") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50)
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

