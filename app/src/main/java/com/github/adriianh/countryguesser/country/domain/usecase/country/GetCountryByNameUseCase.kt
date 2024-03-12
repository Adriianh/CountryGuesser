package com.github.adriianh.countryguesser.country.domain.usecase.country

import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.repository.CountryRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) : BaseUseCase<String, Country>() {

    override suspend fun execute(params: String): Country {
        return countryRepository.getCountryByName(params).getOrNull()
            ?: throw IllegalStateException("No country found with name $params")
    }
}