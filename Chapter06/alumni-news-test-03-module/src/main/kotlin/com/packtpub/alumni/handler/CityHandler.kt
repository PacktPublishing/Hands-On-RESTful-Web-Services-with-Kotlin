package com.packtpub.alumni.handler

import com.packtpub.alumni.extension.Handler
import com.packtpub.alumni.extension.validate
import com.packtpub.alumni.model.City
import com.packtpub.alumni.model.query.QCity
import io.ebean.Database
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond

class CityHandler(private val database: Database) {

    val query = {
        QCity(database)
    }
    val get: Handler = {
        call.respond(query().findList())
    }

    val getById: Handler = {
        val id = call.parameters["id"]
        val country = id?.let { query().setId(it).findOne() }
        if (country != null) {
            call.respond(country)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    val post: Handler = {
        val city = call.receive<City>()
        city.validate()

        database.save(city)
        call.respond(HttpStatusCode.Created)
    }

    val putById: Handler = {
        val id = call.parameters["id"]

        val city = id?.let {
            query().setId(it).findOne()
        }

        if (city != null) {
            val update = call.receive<City>()
            update.validate()

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

    val deleteById: Handler = {
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