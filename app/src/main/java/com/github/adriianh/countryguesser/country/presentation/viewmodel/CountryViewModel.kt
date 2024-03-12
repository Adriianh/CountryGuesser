package com.github.adriianh.countryguesser.country.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.repository.CountryRepository
import com.github.adriianh.countryguesser.country.domain.usecase.country.GetCountriesByRegionUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.country.GetCountryByCapitalUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.country.GetCountryByCodeUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.country.GetCountryByNameUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.country.GetRandomCountryUseCase
import com.github.adriianh.countryguesser.country.presentation.util.sendEvent
import com.github.adriianh.countryguesser.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val getCountriesByRegionUseCase: GetCountriesByRegionUseCase,
    private val getCountryByCapitalUseCase: GetCountryByCapitalUseCase,
    private val getCountryByCodeUseCase: GetCountryByCodeUseCase,
    private val getCountryByNameUseCase: GetCountryByNameUseCase,
    private val getRandomCountryUseCase: GetRandomCountryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CountryViewState())
    val state = _state.asStateFlow()

    init {
        getCountries()
    }

    private fun handleSuccess(country: Country) {
        _state.update {
            it.copy(selectedCountry = country)
        }
    }

    private fun handleSuccessList(countries: List<Country>) {
        _state.update {
            it.copy(countries = countries)
        }
    }

    private fun handleError(error: Throwable) {
        _state.update {
            it.copy(
                error = error.message
            )
        }

        sendEvent(Event.Toast(error.message ?: "Unknown error"))
    }

    private fun handleLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun getCountriesByRegion(region: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleLoading(true)

            getCountriesByRegionUseCase(region)
                .onSuccess(::handleSuccessList)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun getCountryByCapital(capital: String) {
        viewModelScope.launch(Dispatchers.Default) {
            handleLoading(true)

            getCountryByCapitalUseCase(capital)
                .onSuccess(::handleSuccess)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun getCountryByCode(code: String) {
        viewModelScope.launch(Dispatchers.Default) {
            handleLoading(true)

            getCountryByCodeUseCase(code)
                .onSuccess(::handleSuccess)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun getCountryByName(name: String) {
        viewModelScope.launch(Dispatchers.Default) {
            handleLoading(true)

            getCountryByNameUseCase(name)
                .onSuccess(::handleSuccess)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun getRandomCountry() {
        viewModelScope.launch(Dispatchers.Default) {
            handleLoading(true)

            getRandomCountryUseCase(state.value)
                .onSuccess(::handleSuccess)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun resetSelectedCountry() {
        _state.update {
            it.copy(selectedCountry = null)
        }
    }

    private fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            handleLoading(true)

            countryRepository.getCountries()
                .onRight { countries ->
                    _state.update {
                        it.copy(countries = countries)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }

            handleLoading(false)
        }
    }
}