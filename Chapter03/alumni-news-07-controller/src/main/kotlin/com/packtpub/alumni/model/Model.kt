package com.packtpub.alumni.model

data class City(
    val id: String,
    val countryCode: String,
    val name: String,
    val areaCode: Int?
)

data class University(
    val id: String,
    val city: City,
    val name: String,
    val address: String,
    val webPage: String?
)