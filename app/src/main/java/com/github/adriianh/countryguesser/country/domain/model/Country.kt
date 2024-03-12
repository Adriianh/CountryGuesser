package com.github.adriianh.countryguesser.country.domain.model

data class Country(
    val area: Double,
    val borders: List<String>,
    val capital: List<String>? = null,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val languages: Map<String, String>,
    val flag: String,
    val flags: Flags,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val region: String,
    val subregion: String,
)

data class CoatOfArms(
    val png: String,
    val svg: String
)

data class Flags(
    val alt: String,
    val png: String,
    val svg: String
)

data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)

data class Name(
    val common: String,
    val official: String
)