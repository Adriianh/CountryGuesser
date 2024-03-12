package com.github.adriianh.countryguesser.country.data.remote

import com.github.adriianh.countryguesser.country.domain.model.Country
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getCountries(): List<Country>

    @GET("region/{region}")
    suspend fun getCountriesByRegion(region: String): List<Country>

    @GET("name/{name}")
    suspend fun getCountryByName(name: String): Country

    @GET("capital/{capital}")
    suspend fun getCountryByCapital(capital: String): Country

    @GET("code/{code}")
    suspend fun getCountryByCode(code: String): Country
}