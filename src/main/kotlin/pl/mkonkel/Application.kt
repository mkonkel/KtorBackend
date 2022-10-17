package pl.mkonkel

import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Locations
import io.ktor.server.netty.Netty
import pl.mkonkel.plugins.configureAuthentication
import pl.mkonkel.plugins.configureDI
import pl.mkonkel.plugins.configureDatabase
import pl.mkonkel.plugins.configureRouting
import pl.mkonkel.plugins.configureSerialization

@OptIn(KtorExperimentalLocationsAPI::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        configureDI()
        configureAuthentication()
        configureSerialization()
        configureRouting()
        configureDatabase()
    }.start(wait = true)
}
