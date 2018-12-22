package com.packtpub.kotlinid.expression

// Lambdas as variable
private var numbers: List<Int> = listOf(1, 3, 5, 7, 9)
private val filters: List<Int> = numbers.filter { it >= 5 }

// Lambdas as function
fun lambdas(numbers: List<Long>): List<Long> = numbers.filter { it <= 3 }

// Map some list of object then return only one value
fun maps(numbers: List<Long>): Long = numbers.map { it }.first()

// Return a list of some object
data class Employee(val id: Long, var name: String, val email: String)

fun getEmployees(): List<Employee> = listOf(
        Employee(1, "Budi", "budi@mail.com"),
        Employee(name = "Oktaviyan", email = "oktaviyan@mail.com", id = 2),
        Employee(id = 3, email = "budioktaviyans@gmail.com", name = "Budi Oktaviyan")
)