package com.packtpub.alumni.controller

import com.packtpub.alumni.extension.ValidationException
import io.ebean.DuplicateKeyException
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun StatusPages.Configuration.registerRedirections() {
    exception<DuplicateKeyException> {
        call.respond(HttpStatusCode.Conflict)
    }

    exception<ValidationException> { cause ->
        call.respond(HttpStatusCode.BadRequest, cause.getMessages())
    }
}