package com.packtpub.alumni.controller

import io.ktor.routing.Route
import io.ktor.routing.route

fun Route.version1() {
    route("/v1") {
        city()
        university()
    }
}

fun Route.version2() {
    route("/v2") {
        city()
        university()
    }
}