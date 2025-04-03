package com.example.kunsthandleren.screens

import IBMVGAFontFamily
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kunsthandleren.Artist
import com.example.kunsthandleren.Category
import com.example.kunsthandleren.DataSource
import kotlin.collections.map

@Composable
fun <T> FilterScreen(modifier: Modifier = Modifier, onNextButtonClicked: (T)->Unit, filterContent: List<T> = listOf()) {
    Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        filterContent.map { filter ->
            CategoryButton(filter = filter, onNextButtonClicked = onNextButtonClicked)
        }
    }
}

@Composable
fun <T> CategoryButton(
    modifier: Modifier = Modifier,
    filter: T,
    onNextButtonClicked: (T) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onNextButtonClicked(filter) },
        shape = RoundedCornerShape(12.dp),
        color = colorResource(id = android.R.color.darker_gray).copy(alpha = 0.1f), // lys bakgrunn
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            when (filter) {
                is Artist -> {
                    Text(
                        text = filter.name,
                        fontSize = 16.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = IBMVGAFontFamily)
                    )
                    Text(
                        text = filter.familyName,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = IBMVGAFontFamily)
                    )
                }
                is Category -> {
                    Text(
                        text = filter.name,
                        fontSize = 16.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = IBMVGAFontFamily)
                    )
                }
                else -> {
                    Text(
                        text = "Unrecognized Filter Type",
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = IBMVGAFontFamily)
                    )
                }
            }
        }
    }
}



