package com.packtpub.kotlinid.basic

import com.packtpub.kotlinid.first.FirstClass
import com.packtpub.kotlinid.others.FirstClass as OtherFirstClass

fun main(args: Array<String>) {
    val firstClass: FirstClass
    val otherFirstClass: OtherFirstClass

    // Grouping an object with apply
    val config: Config = Config().apply {
        host = "com.domain.foo"
        port = 8080
    }

    val (name, id) = Company(id = 1, name = "Gojek") // Destructuring object declaration
    println("$name, $id")

    val optional: String? = null
    println(optional?.length ?: 0) // Check nullable object with elvis operator
    println(optional?.let { it }.run { "Empty" }) // Check nullable object with let and run

    val students = mapOf("name" to "Budi", "score" to 21)
    println(students.values)

    val lectures = listOf(
            "name" to "Oktaviyan", "faculty" to "Technology",
            "age" to 31
    )
    println(lectures.last())

    bind() // Print some object using with

    // Print an object with a string template
    val year: Long = 1970
    val template: String = "$year"
    println(template)
}

class Config {

    var host: String? = null
    var port: Int = -1
}

data class Company(val name: String, val id: Long)

fun something(param: InlineClass) {} // Call inline class as param in a function

fun bind() {
    with(Company(id = 1, name = "Bhinneka")) {
        println(name)
        println(id)
    }
}