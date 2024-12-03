package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GuessTextField(
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
    label: String,
    leadingIcon: ImageVector,
    iconColor: Color
) {
    OutlinedTextField(
        modifier = Modifier
            .height(70.dp)
            .width(380.dp),
        label = { Text(label) },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF477BC3),
            focusedContainerColor = Color(0xFF477BC3)
        ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        readOnly = readOnly,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = label,
                tint = iconColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = false,
            imeAction = ImeAction.Next
        )
    )
}