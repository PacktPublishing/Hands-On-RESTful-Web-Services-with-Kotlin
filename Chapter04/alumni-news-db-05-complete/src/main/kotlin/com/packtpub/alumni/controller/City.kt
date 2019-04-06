package com.packtpub.alumni.controller

import com.packtpub.alumni.model.City
import com.packtpub.alumni.model.query.QCity
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

fun Route.city() {

    route("/city") {

        val database: Database = get()
        val query = { QCity(get()) }

        get("/") {
            call.respond(query().findList())
        }

        get("/{id}") {
            val id = call.parameters["id"]
            val country = id?.let { query().setId(it).findOne() }
            if (country != null) {
                call.respond(country)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post("/") {
            val city = call.receive<City>()

            database.save(city)
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            val id = call.parameters["id"]

            val city = id?.let {
                query().setId(it).findOne()
            }

            if (city != null) {
                val update = call.receive<City>()

                city.apply {
                    areaCode = update.areaCode
                    name = update.name
                    countryCode = update.countryCode
                }
                database.save(city)

                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]
            val city = id?.let {
                query().setId(it).findOne()
            }
            if (city != null) {
                database.delete(city)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}
