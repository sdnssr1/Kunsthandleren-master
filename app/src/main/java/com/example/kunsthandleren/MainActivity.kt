package com.example.kunsthandleren

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandleren.ui.SystemDesign.KunsthandlerenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KunsthandlerenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Kaller hovedfunksjonen
                    KunsthandlerenApp()
                }
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


@Composable
fun PurchaseScreen(purchaseItemList: List<PurchaseItem>) {
    val ctx = LocalContext.current
    val toast = Toast.makeText(ctx, stringResource(R.string.unimplemented), Toast.LENGTH_SHORT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.total_price),
            fontSize = 16.sp,
            fontWeight = W700
        )
        Text(text = "${calculateTotalPrice(purchaseItemList)}", fontSize = 14.sp)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {toast.show()
            }
        ) {
            Text(text = stringResource(R.string.pay))
        }
    }
}

// Add this function (or move it to where it belongs - e.g., ViewModel)
fun calculateTotalPrice(purchaseItemList: List<PurchaseItem>): Float {
    return purchaseItemList.sumOf { it.price.toDouble() }.toFloat()
}

@Composable
fun PurchaseScreen(purchaseItemList: List<PurchaseItem>) {
    val ctx = LocalContext.current
    val toast = Toast.makeText(ctx, stringResource(R.string.unimplemented), Toast.LENGTH_SHORT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.total_price),
            fontSize = 16.sp,
            fontWeight = W700
        )
        Text(text = "${calculateTotalPrice(purchaseItemList)}", fontSize = 14.sp)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                toast.show()
            }
        ) {
            Text(text = stringResource(R.string.pay))
        }
    }
}

// Add this function (or move it to where it belongs - e.g., ViewModel)
fun calculateTotalPrice(purchaseItemList: List<PurchaseItem>): Float {
    return purchaseItemList.sumOf { it.price.toDouble() }.toFloat()
}

@Composable
fun <T> FilterScreen(
    filterContent: List<T>,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        filterContent.forEach { filter ->
            FilterButton(filter = filter, onItemSelected = onItemSelected)
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Dette er ListScreen")
        KunstnerScreen(navController)
        KategoriScreen(navController)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate(KunstScreens.Detail.route) }) {
            Text("Gå til Detalj")
        }
    }
}

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dette er DetailScreen")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /* TODO: Gå til handlekurv */ }) {
            Text("Kjøp nå")
        }
    }
}

@Composable
fun CartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dette er CartScreen")
    }
}

@Composable
fun KunstnerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("🧑‍🎨 Kunstner-skjerm", style = MaterialTheme.typography.titleLarge)
        // Add content here
    }
}

@Composable
fun KategoriScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("📁 Kategori-skjerm", style = MaterialTheme.typography.titleLarge)
        // Add content here
    }
}


// ============= Navigation =============



@Composable
fun KunsthandlerNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = KunstScreens.Home.route) {
        composable(KunstScreens.Home.route) { HomeScreen(navController) }
        composable(KunstScreens.List.route) { ListScreen(navController) }
        composable(KunstScreens.Detail.route) { DetailScreen(navController) }
        composable(KunstScreens.Cart.route) { CartScreen(navController) }
        composable(KunstScreens.Kunstner.route) { KunstnerScreen(navController) }
        composable(KunstScreens.Kategori.route) { KategoriScreen(navController) }
    }
}

// ============= App + TopBar =============

@Composable
fun KunsthandlerenApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            SubjectTopAppBar(navController)
        }
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            KunsthandlerNavHost(navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTopAppBar(navController: NavController) {
    val currentBackStackEntry = navController.currentBackStackEntry
    val canNavigateBack = navController.previousBackStackEntry != null
            && currentBackStackEntry?.destination?.route != KunstScreens.Home.route

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(
                when (currentRoute) {
                    "home" -> "\uD83C\uDFE0 Kunsthandler"
                    "kunstner" -> "\uD83C\uDFA8 Velg kunstner"
                    "kategori" -> "\uD83D\uDDC2\uFE0F Velg kategori"
                    "pictures" -> "\uD83D\uDCDD Bilder"
                    "detail" -> "📄 Detaljer"
                    "cart" -> "🛒 Kunsthandler"
                    else -> "🧭 Betaling"
                }
            )
        },
        navigationIcon = {
            if (currentRoute != "home") {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Tilbake")
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    KunsthandlerenTheme {
        KunsthandlerenApp()
    }
}
