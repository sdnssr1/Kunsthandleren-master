// In Theme.kt or Typography.kt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontWeight
import com.example.kunsthandleren.R
import androidx.compose.ui.graphics.Color


val IBMVGAFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId =R.font.pxplus_ibm_vga_8x16,
            weight = FontWeight.Bold,
            loadingStrategy = FontLoadingStrategy.Blocking
        )
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF000000),
    onPrimary = Color.White,
    // etc...
)
val MyTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = IBMVGAFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = IBMVGAFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.White
    )
)
