package com.packtpub.alumni.repository

import com.packtpub.alumni.model.City

class CityRepository {
    private val storage = hashSetOf<City>()

    fun save(city: City): City {
        storage.add(city)
        return city
    }

    fun findById(id: String): City? {
        return storage.firstOrNull { it.id == id }
    }

    fun findAll(): List<City> {
        return storage.toList()
    }

    fun findByCountryCode(countryCode: String): List<City> {
        return storage.filter { it.countryCode == countryCode }
    }

    fun count(): Int {
        return storage.size
    }

    fun existsById(id: String): Boolean {
        return storage.any { it.id == id }
    }

}