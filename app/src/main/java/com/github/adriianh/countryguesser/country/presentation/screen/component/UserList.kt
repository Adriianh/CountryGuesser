package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel
import com.primex.core.ExperimentalToolkitApi
import com.primex.core.blur.legacyBackgroundBlur
import com.primex.core.noise
import compose.icons.FeatherIcons
import compose.icons.feathericons.User

@Composable
fun UserList(viewModel: AuthViewModel) {
    val users by viewModel.users.observeAsState()

    Column(
        modifier = Modifier
            .padding(32.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.CenterHorizontally),
            text = "Log In",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(100.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            flingBehavior = ScrollableDefaults.flingBehavior(),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.Start,
            content = {
                items(users?.size ?: 0) { index ->
                    UserCard(
                        user = users!![index],
                        onClick = { user ->
                            viewModel.setCurrentUser(user)
                        }
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalToolkitApi::class)
@Composable
fun UserCard(user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .legacyBackgroundBlur(
                radius = 25f,
                downsample = 0.4f
            )
            .background(Color.White.copy(0.7f))
            .noise(0.05f)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(user) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = FeatherIcons.User,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = user.username,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}