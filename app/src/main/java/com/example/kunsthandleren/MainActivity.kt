package com.example.kunsthandleren

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunsthandleren.theme.KunsthandlerenTheme
import com.example.kunsthandleren.viewmodel.ArtVendorApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KunsthandlerenTheme {
                ArtVendorApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KunsthandlerenTheme {
        ArtVendorApp()
    }
}