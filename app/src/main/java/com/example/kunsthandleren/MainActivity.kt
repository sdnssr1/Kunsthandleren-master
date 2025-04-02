package com.example.kunsthandleren

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunsthandleren.ui.theme.KunsthandlerenTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KunsthandlerenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KunsthandlerenApp(Modifier)
                }
            }
        }
    }
}

@Composable
fun KunsthandlerenApp(modifier: Modifier) {
    Scaffold(
        topBar = { SubjectTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Text("Velg bilde basert på", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { /* TODO */ }) {
                Text("Kunstner")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { /* TODO */ }) {
                Text("Kategori")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTopAppBar(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text("kunsthandleren")
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    KunsthandlerenApp(Modifier)
}