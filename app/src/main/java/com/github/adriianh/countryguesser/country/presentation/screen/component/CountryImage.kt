package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.github.adriianh.countryguesser.country.domain.model.Country

@Composable
fun CountryImage(country: Country) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = country.flags.svg,
        imageLoader = imageLoader,
        modifier = Modifier.size(320.dp, 220.dp),
        contentDescription = "Country Flag",
        contentScale = ContentScale.FillBounds
    )
}