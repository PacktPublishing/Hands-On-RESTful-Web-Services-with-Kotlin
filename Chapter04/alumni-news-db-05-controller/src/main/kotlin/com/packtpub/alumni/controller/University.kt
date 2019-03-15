package com.packtpub.alumni.controller

import com.packtpub.alumni.model.University
import com.packtpub.alumni.model.query.QUniversity
import io.ebean.Database
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import org.koin.ktor.ext.get

fun Route.university() {

    route("/university") {

        val database: Database = get()
        val query = { QUniversity(get()) }

        get("/") {
            call.respond(query().fetch("city").findList())
        }

        get("/{id}") {
            val id = call.parameters["id"]
            val country = id?.let { query().setId(it).fetch("city").findOne() }
            if (country != null) {
                call.respond(country)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post("/") {
            val country = call.receive<University>()

            database.save(country)
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            val id = call.parameters["id"]
            val university = id?.let { query().setId(it).findOne() }

            if (university != null) {
                val update = call.receive<University>()

                university.apply {
                    webPage = update.webPage
                    name = update.name
                    city.id = update.city.id
                    address = update.address
                }

                database.save(university)
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]
            val university = id?.let { query().setId(it).findOne() }
            if (university != null) {
                database.delete(university)

                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}

