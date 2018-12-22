package com.packtpub.kotlinid.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun request() {
    delay(3000)
    val name = "Budi Oktaviyan"
    val age = 31
    println("My name is $name and I was $age") // This will print after 3 seconds
}

fun main(args: Array<String>) {
    runBlocking {
        launch { request() }
        println("Hi,")

        coroutineScope {
            launch { println("---Waiting---") } // This will run after scope initialization
            println("---Prepare---") // This will run first
        }
    }
}