package com.packtpub.alumni.controller

import com.packtpub.alumni.handler.UniversityHandler
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import org.koin.ktor.ext.get

fun Route.university() {

    route("/university") {

        val handler: UniversityHandler = get()

        get("/", handler.get)

        get("/{id}", handler.getById)

        post("/", handler.post)

        put("/{id}", handler.put)

        delete("/{id}", handler.deleteById)

    }
}

