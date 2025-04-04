package com.example.kunsthandleren.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.R

val IBMVGAFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.pxplus_ibm_vga_9x16,
            weight = FontWeight.Normal
        )
    )
)


val MyTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = IBMVGAFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = IBMVGAFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = IBMVGAFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    )
)

@Composable
fun KunsthandlerenTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
        content = content
    )
}
