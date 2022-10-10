package pl.mkonkel.features.games.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.mkonkel.features.games.GamesFeature
import pl.mkonkel.features.games.data.Game
import pl.mkonkel.features.games.data.GameRequest

fun Route.gamesRouting() {
    route("/games") {
        val gamesRepo = GamesFeature.repository

        get {
            val games = gamesRepo.getGames()

            if (games.isNotEmpty()) {
                call.respond(games)
            } else {
                call.respondText("There are no games in our shop yet...", status = HttpStatusCode.OK)
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