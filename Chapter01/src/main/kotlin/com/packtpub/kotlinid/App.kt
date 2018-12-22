package com.packtpub.kotlinid

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}