package com.packtpub.kotlinid.extension

import com.packtpub.kotlinid.basic.DefaultClass
import com.packtpub.kotlinid.expression.Employee

// Typealias from data type
typealias Number = Int

// Typealias from an object
typealias Something = Employee

// Typealias from higher order function
typealias Predicate<T> = (T) -> Boolean

// Typealias from inner class
typealias Inner = DefaultClass.InnerClass