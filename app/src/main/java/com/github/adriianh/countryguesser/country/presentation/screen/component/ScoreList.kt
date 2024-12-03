package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel

@Composable
fun TopScoresList(viewModel: AuthViewModel) {
    val users by viewModel.users.observeAsState()
    val tops = users?.filter { it.score > 0 }
        ?.sortedByDescending { it.score }
        ?.take(10)

    if (tops.isNullOrEmpty()) {
        Text(
            text = "No scores yet",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        tops.forEachIndexed { index, user ->
            TopScoreCard(user, index + 1)
        }
    }
}

@Composable
fun TopScoreCard(user: User, position: Int) {
    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "${position}. ${user.username} - ${user.score}",
            fontFamily = FontFamily(Font(R.font.monocraft_regular)),
            fontSize = 16.sp
        )
    }
}