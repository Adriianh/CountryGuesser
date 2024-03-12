package com.github.adriianh.countryguesser.country.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.ui.theme.BlueDark

@Preview
@Composable
internal fun HomeScreen() {
    val uiColor = if (isSystemInDarkTheme()) BlueDark else Color.White

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(uiColor),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_background),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}