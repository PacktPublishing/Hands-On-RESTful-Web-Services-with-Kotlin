package com.packtpub.kotlinid.basic

class Variables {

    val name: String = "Budi" // Explicit type
    val age = 31 // Implicit type
    lateinit var lateinits: String // Lazy initialization variable
    private val privates: String = "Budi Oktaviyan" // Private initialization variable
    internal var internals: String = "Budi" // Internal initialization variable
}

open class OtherVariables {

    protected val protecteds: String = "Oktaviyan" // Protected initialization variable
}

fun main(args: Array<String>) {
    var status = true // Modified variable
    println("Status = $status")

    status = false
    println("Update status = $status")

    val optionals: String? = "Oktaviyan" // Optional variable
    println(optionals?.length)

    var modifieds: String? = null // Optional modified variable
    println("${modifieds?.length}")

    modifieds = "31"
    println("${modifieds.length}")
}