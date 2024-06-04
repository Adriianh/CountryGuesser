package com.github.adriianh.countryguesser.country.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.screen.component.GameList
import com.github.adriianh.countryguesser.country.presentation.ui.theme.backgroundColor
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel

@Preview
@Composable
internal fun HomeScreen() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Game Modes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 16.dp,
                            bottom = 12.dp
                        )
                )

                GameList()
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Top Scores",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 16.dp,
                            bottom = 12.dp
                        )
                )
            }
        }
    }
}