package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adriianh.countryguesser.R
import compose.icons.FeatherIcons
import compose.icons.feathericons.Play

@Composable
fun GameCard(
    index: Int,
    onClick: () -> Unit
) {
    val game = games[index]
    var lastItemPadding = 0.dp

    if (index == games.size - 1) {
        lastItemPadding = 16.dp
    }

    Box(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = lastItemPadding
            )
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(game.color)
                .width(250.dp)
                .height(160.dp)
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
                .clickable { onClick() },
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = game.name,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                fontSize = 15.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = game.image),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            FilledTonalButton(
                onClick = { onClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PLAY",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = FeatherIcons.Play,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}