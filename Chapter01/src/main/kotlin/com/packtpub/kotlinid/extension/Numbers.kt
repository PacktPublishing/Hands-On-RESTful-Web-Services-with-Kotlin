package com.packtpub.kotlinid.extension

import com.packtpub.kotlinid.expression.Employee

// Extension function from an integer
fun Int.port() {
    this.plus(8000)
}

// Extension function that return an integer
fun Long.parse(param: String): Int = (this.plus(param.length)).toInt()

// Extension function from boolean
fun Boolean.visibility(type: Int): Boolean =
        when (type) {
            1 -> this
            else -> false
        }

// Extension function from some object
fun Employee.names(): List<String> = listOf(this.name)