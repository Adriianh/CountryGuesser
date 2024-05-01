package com.github.adriianh.countryguesser.country.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Azure = Color(0xFF2E83EC)
val BleuDeFrance = Color(0xFF348BE7)
val JordyBlue = Color(0xFFACCCf7)
val Charcoal = Color(0xFF334155)
val LapisBlue = Color(0xFF184079)

val EerieBlack = Color(0xFF191919)

val backgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) LapisBlue else Color.White