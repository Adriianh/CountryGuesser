package com.github.adriianh.countryguesser.country.presentation.viewmodel

import com.github.adriianh.countryguesser.country.domain.model.Country

data class CountryViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val countries: List<Country> = emptyList(),
    val selectedCountry: Country? = null,
    var hasNextCountry: Boolean = true
)