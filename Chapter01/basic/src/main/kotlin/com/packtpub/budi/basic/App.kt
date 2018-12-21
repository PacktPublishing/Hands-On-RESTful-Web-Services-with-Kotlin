package com.packtpub.budi.basic

class App {
    val greeting: String
        get() {
            return "Hello world!"
        }
}

fun main(args: Array<String>) {
    println("${App().greeting} ${hello()}")

    val fix = "This is fix"
    println(fix)

    val optional: String? = null
    println(optional?.length ?: 0)

    var modified = 3
    println(modified)

    modified = 2
    println("Modified to $modified")

    val myArgument: String = myArguments(theSecond = 3, theFirst = "One")
    println(myArgument)

    val myDefaultArgument: String = myDefaultArguments(isDefault = true, x = 5)
    println(myDefaultArgument)

    val template: String = myTemplate(9)
    println(template)

    val ifExpression: String = myIfExpression(true)
    println(ifExpression)

    val ifNotExpression: String = myIfExpression(false)
    println(ifNotExpression)

    val typeExpression: String = myExpression(0)
    println(typeExpression)

    val tryExpression: String = myTryExpression()
    println(tryExpression)
}

fun hello(): String = "Budi"

fun myArguments(theFirst: String, theSecond: Int): String = "$theFirst, $theSecond"

fun myDefaultArguments(x: Int, param: String = "Default", isDefault: Boolean): String = "$x | $isDefault | $param"

fun myTemplate(number: Int): String = "$number"

fun myIfExpression(isAvailable: Boolean): String = if (isAvailable) "Available" else "Not Available"

fun myExpression(type: Int): String =
        when (type) {
            0 -> "Zero type"
            1 -> "First type"
            else -> "Unknown type"
        }

fun myTryExpression(): String =
        try {
            "I have tried"
        } catch (e: Exception) {
            "$e.printStackTrace()"
        }