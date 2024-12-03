package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.User

@Composable
fun UserList(viewModel: AuthViewModel) {
    val users by viewModel.users.observeAsState()

    Column(
        modifier = Modifier
            .padding(32.dp)
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        Text(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.CenterHorizontally),
            text = "LOG IN",
            fontFamily = FontFamily(Font(R.font.monocraft_bold)),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(40.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            flingBehavior = ScrollableDefaults.flingBehavior(),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.Start,
            content = {
                items(users?.size ?: 0) { index ->
                    UserCard(
                        user = users!![index]
                    ) {
                        viewModel.setCurrentUser(users!![index])
                    }
                }
            }
        )
    }
}

@Composable
fun UserCard(user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 4.dp,
            color = Color.Black
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { onClick(user) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = FeatherIcons.User,
                contentDescription = "User Icon: ${user.username}",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(
                    text = user.username,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${user.score} points",
                    fontFamily = FontFamily(Font(R.font.monocraft_regular)),
                    fontSize = 14.sp
                )
            }
        }
    }
}