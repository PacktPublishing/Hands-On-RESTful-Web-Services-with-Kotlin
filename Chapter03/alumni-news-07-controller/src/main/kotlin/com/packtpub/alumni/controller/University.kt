package com.packtpub.alumni.controller

import com.packtpub.alumni.model.University
import com.packtpub.alumni.repository.UniversityRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.university() {
    val repository = UniversityRepository()

    route("/university") {

        get("/") {
            call.respond(repository.findAll())
        }

        get("/{id}") {
            val id = call.parameters["id"]
            val country = id?.let { i -> repository.findById(i) }
            if (country != null) {
                call.respond(country)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post("/") {
            val country = call.receive<University>()
            repository.save(country)
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            val id = call.parameters["id"]
            val university = call.receive<University>()
            if (id != null && repository.existsById(id)) {
                repository.deleteById(id)
                repository.save(university)

                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]
            if (id != null && repository.existsById(id)) {
                repository.deleteById(id)

                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}

