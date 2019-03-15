package com.packtpub.alumni

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Application.alumniModule() {
    install(ContentNegotiation) {
        gson {
        }
    }


    routing {
        get("/") {
            call.respondText("Hello World")
        }

    }

}


