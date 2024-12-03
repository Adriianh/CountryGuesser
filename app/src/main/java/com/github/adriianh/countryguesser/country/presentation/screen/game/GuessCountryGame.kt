package com.github.adriianh.countryguesser.country.presentation.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.presentation.screen.component.CountryImage
import com.github.adriianh.countryguesser.country.presentation.screen.component.GuessTextField
import com.github.adriianh.countryguesser.country.presentation.screen.component.ShowDialogs
import com.github.adriianh.countryguesser.country.presentation.ui.theme.backgroundColor
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel
import com.github.adriianh.countryguesser.country.presentation.viewmodel.CountryViewModel
import com.github.adriianh.countryguesser.country.presentation.viewmodel.CountryViewState
import com.github.adriianh.countryguesser.country.presentation.viewmodel.UserViewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.Map
import compose.icons.feathericons.MapPin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessCountryGame() {
    val countryViewModel: CountryViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()

    val user by authViewModel.selectedAccount.collectAsState()
    var score by remember { mutableIntStateOf(user?.score ?: 0) }

    val state by countryViewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var capital by remember { mutableStateOf("") }
    var answersChecked by remember { mutableStateOf(false) }
    var isAnswerDialogVisible by remember { mutableStateOf(false) }
    var isCheckedDialogVisible by remember { mutableStateOf(false) }
    var isStateDialogVisible by remember { mutableStateOf(false) }
    var isInfoDialogVisible by remember { mutableStateOf(false) }

    var nameBorderColor by remember { mutableStateOf(Color.White) }
    var capitalBorderColor by remember { mutableStateOf(Color.White) }

    val correctAnswerColor = Color(0xFF12ff1e)
    val incorrectAnswerColor = Color(0xFFff1e1e)

    LaunchedEffect(Unit) {
        countryViewModel.getRandomCountry()
    }

    ShowDialogs(
        isAnswerDialogVisible = isAnswerDialogVisible,
        isCheckedDialogVisible = isCheckedDialogVisible,
        isStateDialogVisible = isStateDialogVisible,
        isInfoDialogVisible = isInfoDialogVisible,
        onDismissAnswerDialog = { isAnswerDialogVisible = false },
        onDismissCheckedDialog = { isCheckedDialogVisible = false },
        onDismissStateDialog = { isStateDialogVisible = false },
        onDismissInfoDialog = { isInfoDialogVisible = false }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                text = "SCORE: $score",
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.selectedCountry?.let { country ->
                CountryImage(country = country)
            } ?: run {
                Text(text = "Error: ${state.error ?: "Unknown error"}")
            }
        }

        Spacer(modifier = Modifier.size(50.dp))

        Text(
            text = "GUESS",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.monocraft_bold)),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(24.dp))

        GuessTextField(
            value = name,
            onValueChange = { name = it },
            readOnly = answersChecked,
            label = "Country",
            leadingIcon = FeatherIcons.Map,
            iconColor = nameBorderColor
        )

        Spacer(modifier = Modifier.size(8.dp))

        GuessTextField(
            value = capital,
            onValueChange = { capital = it },
            readOnly = answersChecked,
            label = "Capital",
            leadingIcon = FeatherIcons.MapPin,
            iconColor = capitalBorderColor
        )

        Spacer(modifier = Modifier.size(24.dp))

        FilledTonalButton(
            modifier = Modifier
                .height(50.dp)
                .width(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEDAC2F)
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                if (answersChecked) {
                    isCheckedDialogVisible = true

                    return@FilledTonalButton
                }

                onCheckAnswerClick(
                    state = state,
                    name = name,
                    capital = capital,
                    checkAnswer = ::checkAnswer,
                    updateColors = { isCountryCorrect, isCapitalCorrect ->
                        nameBorderColor =
                            if (isCountryCorrect) correctAnswerColor else incorrectAnswerColor
                        capitalBorderColor =
                            if (isCapitalCorrect) correctAnswerColor else incorrectAnswerColor
                    },
                    updateScore = { isCountryCorrect, isCapitalCorrect ->
                        val newScore =
                            score + if (isCountryCorrect) 5 else 0 + if (isCapitalCorrect) 3 else 0
                        if (newScore > score) {
                            score = newScore
                            userViewModel.updateUserScore(newScore)
                        }
                    },
                    updateResult = { answersChecked = true },
                    showInfoDialog = {
                        isInfoDialogVisible = true
                    }
                )
            }
        ) {
            Text(
                text = "CHECK",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        FilledTonalButton(
            modifier = Modifier
                .height(50.dp)
                .width(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEDAC2F)
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                onNextClick(
                    state = state,
                    answersChecked = answersChecked,
                    showAnswerDialog = { isAnswerDialogVisible = true },
                    showStateDialog = { isStateDialogVisible = true },
                    resetFields = {
                        answersChecked = false
                        name = ""
                        capital = ""
                        nameBorderColor = Color.White
                        capitalBorderColor = Color.White
                    },
                    getRandomCountry = countryViewModel::getRandomCountry
                )
            }
        ) {
            Text(
                text = "NEXT ",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.monocraft_bold)),
                fontSize = 18.sp
            )
        }
    }
}

private fun checkAnswer(country: Country, name: String, capital: String): Pair<Boolean, Boolean> {
    val isCountryCorrect =
        country.name.official.equals(name, ignoreCase = true) || country.name.common.equals(
            name,
            ignoreCase = true
        )

    val isCapitalCorrect = country.capital?.any {
        it.equals(capital, ignoreCase = true)
    } == true

    return Pair(isCountryCorrect, isCapitalCorrect)
}

private fun onCheckAnswerClick(
    state: CountryViewState,
    name: String,
    capital: String,
    checkAnswer: (Country, String, String) -> Pair<Boolean, Boolean>,
    updateColors: (Boolean, Boolean) -> Unit,
    updateScore: (Boolean, Boolean) -> Unit,
    updateResult: () -> Unit,
    showInfoDialog: () -> Unit
) {
    if (name.isBlank() || capital.isBlank()) {
        showInfoDialog()
        return
    }

    val (isCountryCorrect, isCapitalCorrect) = checkAnswer(state.selectedCountry!!, name, capital)
    updateColors(isCountryCorrect, isCapitalCorrect)
    updateScore(isCountryCorrect, isCapitalCorrect)
    updateResult()
}

private fun onNextClick(
    state: CountryViewState,
    answersChecked: Boolean,
    showAnswerDialog: () -> Unit,
    showStateDialog: () -> Unit,
    resetFields: () -> Unit,
    getRandomCountry: () -> Unit
) {
    if (!answersChecked) {
        showAnswerDialog()
        return
    }

    if (!state.hasNextCountry) {
        showStateDialog()
        return
    }

    resetFields()
    getRandomCountry()
}