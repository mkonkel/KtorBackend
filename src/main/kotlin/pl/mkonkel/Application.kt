package pl.mkonkel

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import pl.mkonkel.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
