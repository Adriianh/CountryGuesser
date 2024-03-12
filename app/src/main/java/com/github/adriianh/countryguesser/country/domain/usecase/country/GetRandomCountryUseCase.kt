package com.github.adriianh.countryguesser.country.domain.usecase.country

import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.repository.CountryRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import com.github.adriianh.countryguesser.country.presentation.viewmodel.CountryViewState
import javax.inject.Inject

class GetRandomCountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) : BaseUseCase<CountryViewState, Country>() {

    private val selectedCountries by lazy { mutableListOf<Country>() }

    override suspend fun execute(params: CountryViewState): Country {
        val availableCountries = params.countries
            .filterNot { selectedCountries.contains(it) }

        if (availableCountries.isEmpty()) {
            params.hasNextCountry = false
            throw IllegalStateException("No more countries available")
        }
        val country = availableCountries.random().let {
            selectedCountries.add(it)
            it
        }

        return country
    }

    fun resetSelectedCountries() {
        selectedCountries.clear()
    }
}