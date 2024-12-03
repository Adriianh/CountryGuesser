package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adriianh.countryguesser.R
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import compose.icons.feathericons.Info

@Composable
fun InfoDialog(onDismiss: () -> Unit = {}, title: String, message: List<String>) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        icon = {
            Icon(
                imageVector = FeatherIcons.Info,
                contentDescription = "Info Dialog Icon",
                modifier = Modifier.size(48.dp)
            )
        },
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.monocraft_bold))
            )
        },
        text = {
            Column {
                message.forEach {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.monocraft_regular))
                    )
                }
            }
        },
        confirmButton = {
            IconButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = FeatherIcons.Check,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun ShowDialogs(
    isAnswerDialogVisible: Boolean,
    isCheckedDialogVisible: Boolean,
    isStateDialogVisible: Boolean,
    isInfoDialogVisible: Boolean,
    onDismissAnswerDialog: () -> Unit,
    onDismissCheckedDialog: () -> Unit,
    onDismissStateDialog: () -> Unit,
    onDismissInfoDialog: () -> Unit
) {
    if (isAnswerDialogVisible) {
        InfoDialog(
            onDismiss = onDismissAnswerDialog,
            title = "Answers Not Checked",
            message = listOf("Please check the answer before proceeding.")
        )
    }

    if (isCheckedDialogVisible) {
        InfoDialog(
            onDismiss = onDismissCheckedDialog,
            title = "Answers Checked",
            message = listOf("The answers have already been checked.")
        )
    }

    if (isStateDialogVisible) {
        InfoDialog(
            onDismiss = onDismissStateDialog,
            title = "Game Over",
            message = listOf(
                "You have reached the end of the game.",
                "You can start a new game by clicking the button below.",
                "",
                "Your score is: 0"
            )
        )
    }

    if (isInfoDialogVisible) {
        InfoDialog(
            onDismiss = onDismissInfoDialog,
            title = "Empty Fields",
            message = listOf("Please fill in the country and capital fields before proceeding.")
        )
    }
}