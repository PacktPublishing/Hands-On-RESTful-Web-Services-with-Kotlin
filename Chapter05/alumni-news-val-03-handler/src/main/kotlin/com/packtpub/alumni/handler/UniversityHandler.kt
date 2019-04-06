package com.packtpub.alumni.handler

import com.packtpub.alumni.extension.Handler
import com.packtpub.alumni.extension.validate
import com.packtpub.alumni.model.University
import com.packtpub.alumni.model.query.QUniversity
import io.ebean.Database
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond

class UniversityHandler(private val database: Database) {
    val query = {
        QUniversity(database)
    }

    val get: Handler = {
        call.respond(query().fetch("city").findList())
    }

    val getById: Handler = {
        val id = call.parameters["id"]
        val country = id?.let { query().setId(it).fetch("city").findOne() }
        if (country != null) {
            call.respond(country)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    val post: Handler = {
        val country = call.receive<University>()
        country.validate()

        database.save(country)
        call.respond(HttpStatusCode.Created)
    }

    val put: Handler = {
        val id = call.parameters["id"]
        val university = id?.let { query().setId(it).findOne() }

        if (university != null) {
            val update = call.receive<University>()
            update.validate()

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

    val deleteById: Handler = {
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
