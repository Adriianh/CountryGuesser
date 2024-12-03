package com.github.adriianh.countryguesser.country.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.screen.component.GameList
import com.github.adriianh.countryguesser.country.presentation.screen.component.TopScoresList
import com.github.adriianh.countryguesser.country.presentation.ui.theme.backgroundColor
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut

@Composable
internal fun HomeScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(
                    top = 16.dp
                )
        ) {
            /*
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            */

            Column(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GAME MODES",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 16.dp,
                            bottom = 12.dp
                        )
                )

                GameList(navController)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "TOP SCORES",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 16.dp,
                            bottom = 12.dp
                        )
                )
                TopScoresList(viewModel)
            }

            FloatingActionButton(
                onClick = {
                    viewModel.logout()
                },
                containerColor = Color(0xFF9BB2E5),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = FeatherIcons.LogOut,
                    contentDescription = "Log Out",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}