package pl.mkonkel.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import pl.mkonkel.features.games.presentation.gamesRouting
import pl.mkonkel.features.orders.presentation.ordersRouting

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        gamesRouting()
        ordersRouting()
    }
}
