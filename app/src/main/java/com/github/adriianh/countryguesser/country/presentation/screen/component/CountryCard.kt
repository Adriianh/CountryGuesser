package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.domain.model.Country

@Composable
fun CountryCard(
    country: Country
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        ) {
            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = country.flags.svg,
                    imageLoader = imageLoader,
                    modifier = Modifier.size(240.dp),
                    contentDescription = "Country Flag",
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = country.name.official,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = country.capital?.joinToString(", ") ?: "No capital found",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.monocraft_regular)),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}