package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.presentation.ui.theme.Azure
import com.github.adriianh.countryguesser.country.presentation.ui.theme.JordyBlue
import com.github.adriianh.countryguesser.country.presentation.ui.theme.backgroundColor
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckCircle
import compose.icons.feathericons.User

@Composable
fun RegisterForm(viewModel: AuthViewModel) {
    var valueInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            value = valueInput,
            onValueChange = {
                valueInput = it
            },
            label = {
                Text(
                    text = "Type your username",
                    color = Color.White
                )
            },
            placeholder = { Text(text = "Type here") },
            leadingIcon = {
                Icon(imageVector = FeatherIcons.User, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (valueInput.isEmpty()) {
                        valueInput = "Anonymous"
                    }

                    viewModel.insertUser(
                        User(username = valueInput)
                    )
                })
            ,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF477BC3),
                focusedContainerColor = Color(0xFF477BC3)
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        FilledTonalButton(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.insertUser(
                    User(username = valueInput)
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEDAC2F)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Register")
        }
    }
}