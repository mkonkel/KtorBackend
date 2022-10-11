package pl.mkonkel

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.locations.*
import io.ktor.server.netty.*
import pl.mkonkel.plugins.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
