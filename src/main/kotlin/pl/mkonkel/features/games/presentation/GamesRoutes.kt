package pl.mkonkel.features.games.presentation

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import pl.mkonkel.features.games.GamesFeature
import pl.mkonkel.features.games.data.GameRequest

fun Route.gamesRouting() {
    route("/games") {
        val gamesRepo = GamesFeature.repository

        get {
            val games = gamesRepo.getGames()

            if (games.isNotEmpty()) {
                call.respond(games)
            } else {
                call.respondText(text = "There are no games in our shop yet...", status = HttpStatusCode.OK)
            }
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                text = "Id is required",
                status = HttpStatusCode.BadRequest
            )

            val game = gamesRepo.getGame(id) ?: return@get call.respondText(
                text = "Game with $id does not exist",
                status = HttpStatusCode.NotFound
            )

            call.respond(game)
        }

        post {
            val gameRequest = call.receive<GameRequest>()
            val game = gamesRepo.addGame(gameRequest)
            call.respond(
                status = HttpStatusCode.Created,
                message = game
            )
        }
    }
}