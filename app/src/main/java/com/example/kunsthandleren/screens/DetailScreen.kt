package com.example.kunsthandleren.screens

import IBMVGAFontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.FrameType
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.viewmodel.ArtVendorViewModel


@Composable
fun ImagePreviewScreen(
    modifier: Modifier = Modifier,
    photo: Photo,
    onNextButtonClicked: (PurchaseItem?) -> Unit,
    viewModel: ArtVendorViewModel

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

        // Slider for image width
        val cmPerDp = 2.54f / 160f
        val widthInCm = imageWidth.value * cmPerDp

        LaunchedEffect(widthInCm) {
            viewModel.updateSelectedWidthInCm(widthInCm)
        }


        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        )
                ) {
                    append("Image Width: ")
                }
                withStyle (
                    SpanStyle(
                        color = Color.Blue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = IBMVGAFontFamily
                    )
                ) {
                    append("%.1f ".format(widthInCm))
                }
                withStyle (
                    SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = IBMVGAFontFamily
                    )
                ) {
                    append("cm²".format(widthInCm))
                }
            },
        )
        Slider(
            value = imageWidth.value,
            onValueChange = { newValue ->
                imageWidth = newValue.dp
                newValue * (2.54f / 160f)
                viewModel.updateSelectedWidthInCm(widthInCm)
                            },
            valueRange = 100f..350f,
            steps = 0,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Frame material selection
        Text(
            text = "Select Frame Material:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp

        )
        SelectFrameType(
            updatePurchaseItem = { newFrameType ->
                purchaseItem = purchaseItem.copy(frameType = newFrameType)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Buttons for actions
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { onNextButtonClicked(purchaseItem) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Legg i handlekurv",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Button(
                onClick = { onNextButtonClicked(null) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                )
            ) {
                Text(
                    text = "Hjem",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}



@Composable
fun SelectFrameType(
    modifier: Modifier = Modifier,
    frameTypes: List<FrameType> = listOf(
        FrameType.WOOD,
        FrameType.METAL,
        FrameType.NICE,
        FrameType.GOLD
    ),
    updatePurchaseItem: (FrameType) -> Unit
) {
    var selected by remember { mutableStateOf(frameTypes.first()) }
    Column(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(frameTypes) { frameType ->
            Column(
                modifier = Modifier
                    .clickable {
                        selected = frameType
                        updatePurchaseItem(frameType)
                    }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

                ) {
                val orientationText = if
                        (frameType.title == "Wood" || frameType.title == "Nice") {
                    "Vertical"
                } else if (frameType.title == "Metal" || frameType.title == "Gold") {
                    "Horizontal"
                } else {
                    ""
                }
                Spacer(modifier = Modifier.height(24.dp))

                if (orientationText.isNotEmpty()) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(orientationText)
                            }
                        }
                    )
                }
                Image(
                    painter = painterResource(id = frameType.imageRes),
                    contentDescription = frameType.title,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = frameType.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
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
        if (frameTypes.size > 1) {
        Text(
            text = "Sveip hvis rammmer er utenfor skjermen",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontFamily = IBMVGAFontFamily,
                ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImagePreviewWithFrame(
    photo: Photo,
    frameType: FrameType,
    imageWidth: Dp
) {
    val photoPainter = painterResource(photo.imageResId)
    val framePainter = painterResource(frameType.imageRes)

    Box(modifier = Modifier.size(imageWidth)) {
        Image(
            painter = framePainter,
            contentDescription = "Frame: ${frameType.title}",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
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


