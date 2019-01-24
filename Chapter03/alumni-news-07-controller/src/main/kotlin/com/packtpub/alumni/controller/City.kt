package com.packtpub.alumni.controller

import com.packtpub.alumni.model.City
import com.packtpub.alumni.repository.CityRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route

fun Route.city() {
    val repository = CityRepository()

    route("/city") {

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
            val country = call.receive<City>()
            repository.save(country)
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            val id = call.parameters["id"]
            val city = call.receive<City>()
            if (id != null && repository.existsById(id)) {
                repository.deleteById(id)
                repository.save(city)

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

