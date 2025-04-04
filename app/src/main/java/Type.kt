import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontWeight
import com.example.kunsthandleren.R


val IBMVGAFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId =R.font.pxplus_ibm_vga_9x16,
            weight = FontWeight.Bold,
            loadingStrategy = FontLoadingStrategy.Blocking
        )
    )
)

