package com.packtpub.kotlinid.structure

// You can type in the top level with or without private modifier
private val strings: String = "This is string"
private val ints: Int = 1
private val longs: Long = 10
private val doubles: Double = 1.0
private val floats: Float = 10.0f
private val shorts: Short = 100
private val bytes: Byte = 50

// You can create your own new data type based on your needs
private typealias Radius = (Double, Int) -> Double