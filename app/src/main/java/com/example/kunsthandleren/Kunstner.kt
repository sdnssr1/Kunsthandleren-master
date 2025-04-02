package com.example.kunsthandleren

import androidx.compose.runtime.Composable

@Composable
fun KunstnerScreen(onArtistSelected: (Artist) -> Unit) {
    FilterScreen(
        filterContent = DataSource.artists,
        onNextButtonClicked = { artist ->
            if (artist is Artist) {
                onArtistSelected(artist)
            }
        }
    )
}
