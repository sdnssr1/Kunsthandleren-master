package com.example.kunsthandleren.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.R

@Composable
fun ImageScreen(modifier: Modifier = Modifier, photos: List<Photo> = listOf(), onNextButtonClick: (Photo) -> Unit = {}) {

    Column(modifier.fillMaxSize()) {
        if(photos.count() < 1){
            Text(stringResource(R.string.no_images_found))}
        else {
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    top = 16.dp,   // extra space from the top
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(photos) { photo ->
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // adjust this height to make the image "big" as desired
                            .clickable { onNextButtonClick(photo) },
                        painter = painterResource(photo.imageResId),
                        contentDescription = "${photo.title} ${photo.artist.name} ${photo.artist.familyName}",
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagePreview(){
    Column{
        ImageScreen()
        ImageScreen(photos = DataSource.photos)
    }
}