package com.example.kunsthandleren

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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


enum class KunstScreens(val route: String) {
    Home("home"),
    List("list"),
    Detail("detail"),
    Cart("cart"),
    Kunstner("kunstner"),
    Kategori("kategori")
}


// ============= Screens =============

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Velg bilde basert på",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Button(onClick = { navController.navigate(KunstScreens.Kunstner.route) }) {
                Text("Kunstner")
            }
            Button(onClick = { navController.navigate(KunstScreens.Kategori.route) }) {
                Text("Kategori")
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