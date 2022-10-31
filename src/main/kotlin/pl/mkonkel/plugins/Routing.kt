package pl.mkonkel.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import pl.mkonkel.features.games.presentation.gamesRouting
import pl.mkonkel.features.login.presentation.loginRouting
import pl.mkonkel.features.orders.presentation.ordersRouting
import pl.mkonkel.features.users.presentation.usersRouting

fun Application.configureRouting() {
    install(Routing)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        gamesRouting()
        loginRouting()
        ordersRouting()
        usersRouting()
    }
}
