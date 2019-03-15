package com.packtpub.alumni

import com.packtpub.alumni.controller.city
import com.packtpub.alumni.controller.university
import com.packtpub.alumni.module.dataSourceModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.get
import org.koin.ktor.ext.installKoin
import javax.sql.DataSource

@KtorExperimentalAPI
fun Application.alumniModule() {
    install(ContentNegotiation) {
        gson {
        }
    }

    installKoin {
        slf4jLogger()

        modules(dataSourceModule)
    }


    routing {
        val dataSource = get<DataSource>()
        get("/") {
            call.respondText("Hello World, Connection Closed = ${dataSource.connection.isClosed}")
        }

        city()
        university()
    }

}


