package com.packtpub.kotlinid.expression

// Higher order function return to integer
fun higherOrders(total: (Int, Int) -> Int) {}

// Higher order function return to unit
fun otherHigherOrders(value: (String, Int) -> Unit) {}

// Higher order function that return something
fun someHigherOrders(total: (Int, Int) -> Int): Int = total(5, 50)

// Higher order as variable
private val params: (Int, Long) -> String = { x, y -> "$x and $y" }