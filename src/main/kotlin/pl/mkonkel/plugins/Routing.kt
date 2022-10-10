package pl.mkonkel.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import pl.mkonkel.features.games.presentation.gamesRouting

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        gamesRouting()
    }
}
