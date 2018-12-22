package com.packtpub.kotlinid.extension

import com.packtpub.kotlinid.expression.Employee

// Extension function from string and return to integer
fun String.count(): Int = this.length

// Extension function from an object and return to string
fun Employee.name(): String = this.name

// Extension function using boolean parameter and return to string
fun String.availability(isAvailable: Boolean): String = "$this == $isAvailable"