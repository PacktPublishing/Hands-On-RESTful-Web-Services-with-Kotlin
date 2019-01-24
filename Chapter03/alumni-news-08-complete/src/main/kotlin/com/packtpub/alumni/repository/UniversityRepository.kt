package com.packtpub.alumni.repository

import com.packtpub.alumni.model.University

class UniversityRepository {
    private val storage = hashSetOf<University>()

    fun save(city: University): University {
        storage.add(city)
        return city
    }

    fun findById(id: String): University? {
        return storage.firstOrNull { it.id == id }
    }

    fun findByCityId(id: String): List<University> {
        return storage.filter { it.city.id == id }
    }

    fun findAll(): List<University> {
        return storage.toList()
    }

    fun count(): Int {
        return storage.size
    }

    fun existsById(id: String): Boolean {
        return storage.any { it.id == id }
    }

    fun deleteById(id: String): Boolean {
        return storage.removeIf { it.id == id }
    }
}