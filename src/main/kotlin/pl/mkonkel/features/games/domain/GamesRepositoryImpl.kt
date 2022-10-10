package pl.mkonkel.features.games.domain

import pl.mkonkel.features.games.data.Game
import pl.mkonkel.features.games.data.GameRequest
import java.util.UUID

internal class GamesRepositoryImpl : GamesRepository {
    private val games: MutableList<Game> = mutableListOf()

    override fun getGames(): List<Game> {
        return games
    }

    override fun addGame(request: GameRequest): Game {
        return Game(
            id = UUID.randomUUID().toString(),
            name = request.name,
            price = request.price,
            genre = request.genre
        )
            .also { games.add(it) }
    }

    override fun getGame(id: String): Game? {
        return games.firstOrNull { it.id == id }
    }
}