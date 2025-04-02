package com.example.kunsthandleren.viewmodel

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.HomeScreen
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.screens.CheckoutScreen
import com.example.kunsthandleren.screens.FilterScreen
import com.example.kunsthandleren.screens.ImagePreviewScreen
import com.example.kunsthandleren.screens.ImageScreen
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
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.teal_200),
            titleContentColor = colorResource(id = R.color.black)
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
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
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = ArtVendorScreen.Start.name) {
                HomeScreen(
                    items = uiState.purchaseItemList,
                    onNextButtonClicked = { filter ->
                        navController.navigate(ArtVendorScreen.Filter.name)
                        viewModel.updateChosenFilter(filter)
                    },
                    onPurchaseClicked = { navController.navigate(ArtVendorScreen.Purchase.name) },
                    onDeleteClicked = { photoId -> viewModel.deleteFromPurchaseItem(photoId) }

                )
            }
            composable(route = ArtVendorScreen.Filter.name) {
                FilterScreen(
                    filterContent = if (uiState.chosenFilter == Filters.ARTIST) DataSource.artists else DataSource.categories,
                    onNextButtonClicked = { chosen ->
                        navController.navigate(ArtVendorScreen.Images.name)
                        viewModel.updateChosenArtistOrCategory(chosen)
                    }
                )
            }
            composable(route = ArtVendorScreen.Images.name) {
                var photos = listOf<Photo>()
                if (uiState.chosenArtist != null) {
                    photos =
                        DataSource.photos.filter { photo -> photo.artist.name == uiState.chosenArtist!!.name && photo.artist.familyName == uiState.chosenArtist!!.familyName }
                }
                if (uiState.chosenCategory != null) {
                    photos =
                        DataSource.photos.filter { photo -> photo.category == uiState.chosenCategory }
                }
                ImageScreen(photos = photos, onNextButtonClick = { photo ->
                    navController.navigate(ArtVendorScreen.ImagePreview.name)
                    viewModel.setTargetPhoto(photo)
                })
            }
            composable(route = ArtVendorScreen.ImagePreview.name) {
                ImagePreviewScreen(
                    photo = uiState.targetPhoto,
                    onNextButtonClicked = { purchaseItem: PurchaseItem? ->
                        if (purchaseItem != null) {
                            viewModel.updatePurchaseItemList(purchaseItem)
                        }
                        navController.navigate(ArtVendorScreen.Start.name)
                    })
            }

            composable(route = ArtVendorScreen.Purchase.name) {
                CheckoutScreen(items = uiState.purchaseItemList)
            }
            composable(route = ArtVendorScreen.Home.name) {
                HomeScreen(navController)
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
