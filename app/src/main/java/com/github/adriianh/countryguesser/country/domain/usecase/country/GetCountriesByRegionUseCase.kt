package com.github.adriianh.countryguesser.country.domain.usecase.country

import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.repository.CountryRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetCountriesByRegionUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) : BaseUseCase<String, List<Country>>() {

    override suspend fun execute(params: String): List<Country> {
        return countryRepository.getCountriesByRegion(params).getOrNull()
            ?: throw IllegalStateException("No countries found with region $params")
    }
}