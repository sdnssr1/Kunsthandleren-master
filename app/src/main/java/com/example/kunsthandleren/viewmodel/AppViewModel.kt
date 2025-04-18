package com.example.kunsthandleren.viewmodel

import IBMVGAFontFamily
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.screens.CheckoutScreen
import com.example.kunsthandleren.screens.FilterScreen
import com.example.kunsthandleren.screens.ImagePreviewScreen
import com.example.kunsthandleren.screens.ImageScreen
import com.example.kunsthandleren.screens.HomeScreen
import kotlin.collections.filter

enum class ArtVendorScreen(@StringRes val title: Int) {
    Start(R.string.visible_name),
    Filter(R.string.filter_screen),
    Images(R.string.images_screen),
    ImagePreview(R.string.image_preview_screen),
    Purchase(R.string.purchase),

    // Dine skjermer
    Home(R.string.home_screen),
    Kunstner(R.string.kunstner_screen),
    Kategori(R.string.kategori_screen),
    Detail(R.string.detail_screen),
    Cart(R.string.cart_screen),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtVendorAppBar(
    modifier: Modifier = Modifier,
    currentScreen: ArtVendorScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(currentScreen.title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = IBMVGAFontFamily,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF0000AA),
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = Color.White // Back icon white
                    )
                }
            }
        }
    )
}

@Composable
fun ArtVendorApp(
    viewModel: ArtVendorViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        ArtVendorScreen.valueOf(backStackEntry?.destination?.route ?: ArtVendorScreen.Start.name)

    Scaffold(topBar = {
        ArtVendorAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null && navController.currentDestination?.route != ArtVendorScreen.Start.name,
            navigateUp = {
                navController.navigateUp()
            })
    }) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ArtVendorScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Home Screen
            composable(route = ArtVendorScreen.Start.name) {
                HomeScreen(
                    items = uiState.purchaseItemList,
                    onNextButtonClicked = { filter ->
                        navController.navigate(ArtVendorScreen.Filter.name)
                        viewModel.updateChosenFilter(filter)
                    },
                    onPurchaseClicked = { navController.navigate(ArtVendorScreen.Purchase.name) }
                )
            }

            // Checkout Screen
            composable(route = ArtVendorScreen.Purchase.name) {
                CheckoutScreen(
                    items = uiState.purchaseItemList,
                    widthInCm = viewModel.selectedWidthInCm,
                    navController = navController,
                    onResetCart = { uiState.purchaseItemList = emptyList() }
                )
            }

            // Filter Screen
            composable(route = ArtVendorScreen.Filter.name) {
                FilterScreen(
                    filterContent = if (uiState.chosenFilter == Filters.ARTIST)
                        uiState.artistList else uiState.categoryList

,
                    onNextButtonClicked = { chosen ->
                        navController.navigate(ArtVendorScreen.Images.name)
                        viewModel.updateChosenArtistOrCategory(chosen)
                    }
                )
            }

            // Images Screen
            composable(route = ArtVendorScreen.Images.name) {
                var photos = listOf<Photo>()
                if (uiState.chosenArtist != null) {
                    photos = DataSource.photos.filter { photo ->
                        photo.artist.name == uiState.chosenArtist!!.name &&
                                photo.artist.familyName == uiState.chosenArtist!!.familyName
                    }
                }
                if (uiState.chosenCategory != null) {
                    photos = DataSource.photos.filter { photo ->
                        photo.category == uiState.chosenCategory
                    }
                }
                ImageScreen(
                    photos = photos,
                    onNextButtonClick = { photo ->
                        viewModel.setTargetPhoto(photo)
                        navController.navigate(ArtVendorScreen.ImagePreview.name)
                    }
                )
            }

            // Image Preview Screen
            composable(route = ArtVendorScreen.ImagePreview.name) {
                // Ensure uiState.targetPhoto is valid.
                uiState.targetPhoto?.let { photo ->
                    ImagePreviewScreen(
                        photo = photo,
                        viewModel = viewModel,
                        onNextButtonClicked = { purchaseItem: PurchaseItem? ->
                            if (purchaseItem != null) {
                                viewModel.updatePurchaseItemList(purchaseItem)
                            }
                            navController.navigate(ArtVendorScreen.Start.name)
                        }
                    )
                } ?: run {
                    // Optionally handle the null case, e.g., navigate back.
                    navController.navigate(ArtVendorScreen.Start.name)
                }
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewArtVendorAppBar() {
    ArtVendorAppBar(
        currentScreen = ArtVendorScreen.Start,
        canNavigateBack = false,
        navigateUp = {}
    )
}
