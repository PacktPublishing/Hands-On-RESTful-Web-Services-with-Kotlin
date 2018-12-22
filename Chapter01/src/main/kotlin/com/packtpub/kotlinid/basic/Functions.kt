package com.packtpub.kotlinid.basic

// Basic function
fun setup() {
    println("Hello!")
}

// Basic return function
fun greetings(): String = "Good Morning!"

// Private function
private fun privates() {
    println("This is private function")
}

// Internal function
internal fun internals() {
    println("Internal use only!")
}

// Inline function
inline fun inlines(types: (Int, Int) -> Int): Int = types(3, 5)

// Inline and reified function
inline fun <reified T : Any> clazz() = T::class.java

// Infix function
infix fun String.similiars(param: String): Boolean = this == param

// Named argument function
fun arguments(name: String, status: Boolean) {
    println("Argument name = $name and status is $status")
}

// Default argument function
fun defaults(name: String, age: Int = 31): String = "Default named argument for $name is $age"

open class Functions {

    // Protected function
    protected fun protecteds(): String = "This is protected function"
}

fun main(args: Array<String>) {
    setup()
    println(greetings())
    privates()
    internals()
    println("${inlines { x, y -> x + y }}")
    println("Budi" similiars "Budi")
    println(clazz<Long>().simpleName)
    arguments(status = false, name = "Visibility")
    println(defaults("Age"))
}