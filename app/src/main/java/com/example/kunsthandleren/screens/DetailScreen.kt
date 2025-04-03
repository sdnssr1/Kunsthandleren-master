package com.example.kunsthandleren.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.FrameSize
import com.example.kunsthandleren.FrameType
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem

import kotlin.math.abs
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale


@Composable
fun ImagePreviewScreen(
    modifier: Modifier = Modifier,
    photo: Photo,
    onNextButtonClicked: (PurchaseItem?) -> Unit
) {
    var purchaseItem by remember { mutableStateOf(PurchaseItem(photo)) }
    var imageWidth by remember { mutableStateOf(200.dp) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Instead of drawing a colored border, use the frame image:
        ImagePreviewWithFrame(
            photo = photo,
            frameType = purchaseItem.frameType,
            imageWidth = imageWidth
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Slider for image width (adjusts the overall frame size)
        Text(text = "Image Width: ${imageWidth.value.toInt()} dp")
        Slider(
            value = imageWidth.value,
            onValueChange = { newValue ->
                imageWidth = newValue.dp
            },
            valueRange = 100f..500f,
            steps = 0,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Frame material selection
        Text(text = "Select Frame Material:")
        SelectFrameType(
            updatePurchaseItem = { newFrameType ->
                purchaseItem = purchaseItem.copy(frameType = newFrameType)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Buttons for actions
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { onNextButtonClicked(null) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0000AA),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Button(
                onClick = { onNextButtonClicked(purchaseItem) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0000AA), // Deep blue background
                    contentColor = Color.White          // White text
                )
            ) {
                Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}



@Composable
fun SelectFrameType(
    modifier: Modifier = Modifier,
    frameTypes: List<FrameType> = listOf(FrameType.WOOD, FrameType.METAL, FrameType.NICE, FrameType.GOLD),
    updatePurchaseItem: (FrameType) -> Unit
) {
    var selected by remember { mutableStateOf(frameTypes.first()) }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(frameTypes) { frameType ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable {
                        selected = frameType
                        updatePurchaseItem(frameType)
                    }
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = frameType.imageRes),
                    contentDescription = frameType.title,
                    modifier = Modifier.size(64.dp)
                )
                Text(text = frameType.title)
                RadioButton(
                    selected = frameType == selected,
                    onClick = {
                        selected = frameType
                        updatePurchaseItem(frameType)
                    }
                )
            }
        }
    }
}


@Composable
fun ImagePreviewWithFrame(
    photo: Photo,
    frameType: FrameType,
    imageWidth: Dp
) {
    // Obtain the painter for the photo and frame
    val photoPainter = painterResource(photo.imageResId)
    val framePainter = painterResource(frameType.imageRes)

    // Assume the frame image is designed to be stretched, for example a 9-patch or PNG with transparent center.
    Box(modifier = Modifier.size(imageWidth)) {
        // Draw the frame image as the background. It fills the entire box.
        Image(
            painter = framePainter,
            contentDescription = "Frame: ${frameType.title}",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        // Draw the photo image centered inside the frame.
        // Adjust the factor (0.8f below) so the photo fits nicely within the frame.
        Image(
            painter = photoPainter,
            contentDescription = "${photo.title} by ${photo.artist.name}",
            modifier = Modifier
                .size(imageWidth * 0.8f)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
fun SelectFrameSize(
    modifier: Modifier = Modifier,
    sizes: List<FrameSize> = DataSource.frameSizes,
    updatePurchaseItem: (FrameSize) -> Unit,
    onImageScaleChange: (Dp) -> Unit // New callback to update image size
) {
    if (sizes.isEmpty()) return

    // Determine the slider's numeric range from available sizes.
    val minValue = sizes.minOf { it.size }.toFloat()
    val maxValue = sizes.maxOf { it.size }.toFloat()

    // Remember the slider value, starting with the first size.
    var sliderValue by remember { mutableStateOf(sizes.first().size.toFloat()) }

    // Helper function to pick the closest FrameSize from the list.
    fun getClosestFrameSize(value: Float): FrameSize {
        return sizes.minByOrNull { abs(it.size - value) } ?: sizes.first()
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(text = "Frame & Image Size: ${sliderValue.toInt()} dp")
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = sliderValue,
            onValueChange = { newValue ->
                sliderValue = newValue
                val closest = getClosestFrameSize(newValue)
                updatePurchaseItem(closest)
                onImageScaleChange(newValue.dp)
            },
            valueRange = minValue..maxValue,
            steps = (sizes.size - 2).coerceAtLeast(0),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ImagePreviewPreview() {
    ImagePreviewScreen(
        modifier = Modifier.fillMaxSize(),
        photo = DataSource.naturePhotos[0],
        onNextButtonClicked = {}
    )
}
