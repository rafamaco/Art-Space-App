package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                ArtSpace()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}

@Composable
fun ArtSpace() {
    val minPosition = 1
    val maxPosition = 3
    var index by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (index) {
            1 -> DisplayArt(
                painter = painterResource(id = R.drawable.first_painting),
                title = stringResource(R.string.first_painting_title),
                description = stringResource(R.string.first_painting_desc),
                modifier = Modifier.weight(1F)
            )

            2 -> DisplayArt(
                painter = painterResource(id = R.drawable.second_painting),
                title = stringResource(R.string.second_painting_title),
                description = stringResource(R.string.first_painting_desc),
                modifier = Modifier.weight(1F)
            )

            3 -> DisplayArt(
                painter = painterResource(id = R.drawable.third_painting),
                title = stringResource(R.string.third_painting_title),
                description = stringResource(R.string.third_painting_desc),
                modifier = Modifier.weight(1F)
            )
        }
        DisplayController(
            previous = {
                if (index > minPosition) index-- },
            next = {
                if (index < maxPosition) index++
                else if (index == maxPosition) index = 1 }
        )
    }
}

@Composable
fun DisplayArt(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    description: String
) {
    ArtworkDisplayImage(
        painter = painter,
        modifier = modifier.fillMaxSize()
    )
    ArtworkDisplayInformation(
        title = title,
        description = description
    )
}

@Composable
fun ArtworkDisplayImage(modifier: Modifier = Modifier, painter: Painter) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_32)))
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_16))
                .border(
                    width = dimensionResource(id = R.dimen.width_32),
                    color = Color.White,
                    shape = RectangleShape
                )
                .shadow(elevation = dimensionResource(id = R.dimen.elevation_8))
        )
    }
}

@Composable
fun ArtworkDisplayInformation(title: String, description: String) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_32)))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.padding_16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )
    }
}

@Composable
fun DisplayController(
    previous: () -> Unit,
    next: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16))
    ) {
        Button(
            onClick = previous,
            modifier = Modifier.weight(1F)
        ) {
            Text(text = stringResource(R.string.btn_previous))
        }
        Spacer(modifier = Modifier.weight(weight = 0.1F))
        Button(
            onClick = next,
            modifier = Modifier.weight(1F)
        ) {
            Text(text = stringResource(R.string.btn_next))
        }
    }
}