package com.github.adriianh.countryguesser.country.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.screen.component.UserList
import com.github.adriianh.countryguesser.country.presentation.ui.theme.backgroundColor
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel

@Composable
internal fun UserLogin() {
    val viewModel: AuthViewModel = hiltViewModel()

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.main_background),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    UserList(viewModel = viewModel)
                }
            }
        }
    }
}