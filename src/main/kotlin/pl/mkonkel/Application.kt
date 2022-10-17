package pl.mkonkel

import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.resources.Resources
import pl.mkonkel.plugins.configureAuthentication
import pl.mkonkel.plugins.configureDI
import pl.mkonkel.plugins.configureDatabase
import pl.mkonkel.plugins.configureRouting
import pl.mkonkel.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Resources)
        configureDI()
        configureAuthentication()
        configureSerialization()
        configureRouting()
        configureDatabase()
    }.start(wait = true)
}
