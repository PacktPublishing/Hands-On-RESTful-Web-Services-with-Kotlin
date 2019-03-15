package com.packtpub.alumni.extension

import javax.validation.ConstraintViolation
import javax.validation.Validation

private val validator by lazy {
    val factory = Validation.buildDefaultValidatorFactory()
    factory.validator
}

fun <T> T.validate() {
    val violation = validator.validate(this)
    if (violation.isNotEmpty()) {
        throw ValidationException(violation = violation)
    }
}

data class ValidationException(val violation: Set<ConstraintViolation<*>>, override val message: String? = null) :
        RuntimeException(message) {
    fun getMessages(): Map<String, String> {
        return violation.map { it.propertyPath.toString() to it.message }.toMap()
    }
}
