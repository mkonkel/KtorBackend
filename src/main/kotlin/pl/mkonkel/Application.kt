package pl.mkonkel

import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.resources.Resources
import io.ktor.server.routing.Routing
import pl.mkonkel.plugins.configureAuthentication
import pl.mkonkel.plugins.configureDI
import pl.mkonkel.plugins.configureDatabase
import pl.mkonkel.plugins.configureRouting
import pl.mkonkel.plugins.configureSerialization
import pl.mkonkel.plugins.configureStatusPages

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Routing)
        install(Resources)
        configureDI()
        configureAuthentication()
        configureSerialization()
        configureRouting()
        configureDatabase()
        configureStatusPages()
    }.start(wait = true)
}
