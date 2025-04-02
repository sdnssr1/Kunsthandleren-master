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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kunsthandleren.DataSource
import com.example.kunsthandleren.FrameSize
import com.example.kunsthandleren.FrameType
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PhotoSize
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.R
import com.example.kunsthandleren.testPhoto
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ImagePreviewScreen(
    modifier: Modifier = Modifier,
    photo: Photo,
    onNextButtonClicked: (PurchaseItem?) -> Unit
) {
    // States for your purchase item, border thickness, and image width
    var purchaseItem by remember { mutableStateOf(PurchaseItem(photo)) }
    var borderThickness by remember { mutableStateOf(4.dp) }
    var imageWidth by remember { mutableStateOf(200.dp) }

    // Painter + aspect ratio for maintaining the photo's shape
    val painter = painterResource(photo.imageResId)
    val intrinsicSize = painter.intrinsicSize
    val aspectRatio = if (intrinsicSize.height != 0f) {
        intrinsicSize.width / intrinsicSize.height
    } else 1f

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box with border that wraps the image
        Box(
            modifier = Modifier.border(
                width = borderThickness,
                color = purchaseItem.frameType.color
            )
        ) {
            // Force the image to maintain aspect ratio, but let user pick width
            Image(
                painter = painter,
                contentDescription = "${photo.title} ${photo.artist.name} ${photo.artist.familyName}",
                modifier = Modifier
                    .width(imageWidth)
                    .aspectRatio(aspectRatio)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Slider for the IMAGE WIDTH ---
        Text(text = "Image Width: ${imageWidth.value.toInt()} dp")
        Slider(
            value = imageWidth.value,
            onValueChange = { newValue ->
                imageWidth = newValue.dp
            },
            valueRange = 100f..500f,
            steps = 0, // continuous
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Slider for the FRAME THICKNESS ---
        Text(text = "Border Thickness: ${borderThickness.value.toInt()} dp")
        Slider(
            value = borderThickness.value,
            onValueChange = { newValue ->
                borderThickness = newValue.dp
            },
            valueRange = 1f..20f,
            steps = 19,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Select Frame Material (if needed) ---
        Text(text = "Select Frame Material:")
        SelectFrameType(
            updatePurchaseItem = { newFrameType ->
                purchaseItem.frameType = newFrameType
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Buttons for "Add to Cart" or "Home" ---
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { onNextButtonClicked(null) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = colorResource(id = R.color.white)
                )
            ) {
                Text(text = "Home")
            }
            Button(
                onClick = { onNextButtonClicked(purchaseItem) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = colorResource(id = R.color.white)
                )
            ) {
                Text(text = "Add to Cart")
            }
        }
    }
}



@Composable
fun SelectFrameType(
    modifier: Modifier = Modifier,
    frameTypes: List<FrameType> = listOf(FrameType.WOOD, FrameType.METAL, FrameType.NICE),
    updatePurchaseItem: (FrameType) -> Unit
) {
    var selected by remember { mutableStateOf(frameTypes.first()) }
    Column(modifier = modifier) {
        frameTypes.forEach { frameType ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = frameType == selected,
                    onClick = {
                        selected = frameType
                        updatePurchaseItem(frameType)
                    }
                )
                Text(text = frameType.name)
            }
        }
    }
}



@Composable
fun SelectPhotoSize(
    modifier: Modifier = Modifier,
    sizes: List<PhotoSize> = DataSource.photoSizes,
    updatePurchaseItem: (PhotoSize) -> Unit
) {
    var selected by remember { mutableStateOf(sizes.first()) }
    Column(modifier = modifier) {
        sizes.forEach { size: PhotoSize ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = size == selected,
                    onClick = {
                        selected = size
                        updatePurchaseItem(size)
                    }
                )
                Text(text = stringResource(id = size.title))
            }
        }
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
                // Pick the closest available frame size.
                val closest = getClosestFrameSize(newValue)
                updatePurchaseItem(closest)
                // Also, update the overall image scale.
                // (You can apply a multiplier here if you want the image size to differ from the frame width.)
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
