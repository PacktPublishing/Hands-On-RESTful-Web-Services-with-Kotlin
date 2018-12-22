package com.packtpub.kotlinid.expression

import com.packtpub.kotlinid.basic.SealedClass
import com.packtpub.kotlinid.basic.SealedClass.DataFirst
import com.packtpub.kotlinid.basic.SealedClass.DataSecond
import com.packtpub.kotlinid.basic.SealedClass.Objects

// If expression as variable
private var text: String? = null
private val check: String = if (null != text) "Not null" else "Null"

// If expression as function
fun ifExpression(isAvailable: Boolean): String = if (isAvailable) "Available" else "Not Available"

// When expression as variable
private val something: String =
        when (text) {
            null -> "String was null"
            else -> "String is not null"
        }

// When expression as function
fun whenExpression(type: Int): String =
        when (type) {
            1 -> "The first"
            2 -> "The second"
            else -> "Unknown type"
        }

// When expression used as casting operation
fun castingExpression(sealedClass: SealedClass): String =
        when (sealedClass) {
            is DataFirst -> "First value is ${sealedClass.type}"
            is DataSecond -> "Second value is ${sealedClass.availability}"
            is Objects -> "This is objects as singleton"
        }

// Try expression as variable
private val trying: String =
        try {
            "I have tried"
        } catch (e: Exception) {
            "$e.printStackTrace()"
        }

// Try expression as function
fun tryExpression(value: String? = null): String =
        try {
            value!! // If you forced to pass nullable object with this, it will throw an exception
        } catch (e: Exception) {
            "${e.printStackTrace()}"
        }