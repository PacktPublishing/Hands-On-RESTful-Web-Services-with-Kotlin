package com.packtpub.kotlinid.basic

// Default class
class DefaultClass {

    inner class InnerClass
}

// Open class
open class OpenClass

// Abstract class
abstract class BaseClass

// Internal class
internal class InternalClass

// Inline class
inline class InlineClass(val name: String)

// Data class
data class DataClass(val value: String)

// Sealed class, you can put any data or object class in a sealed class
sealed class SealedClass {
    data class DataFirst(val type: Int) : SealedClass()
    data class DataSecond(val availability: Boolean) : SealedClass()
    object Objects : SealedClass()
}

// Object as singleton pattern
object Something {

    const val VALUE: String = "SOME_VALUE"
    fun check() {}
}