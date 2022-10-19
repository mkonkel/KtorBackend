package pl.mkonkel.features.games.domain

import pl.mkonkel.features.games.data.Game
import pl.mkonkel.features.games.data.GameRequest
import java.util.UUID

internal class GamesRepositoryImpl : GamesRepository {
    private val games: MutableList<Game> = mutableListOf()

    override fun getGames(): List<Game> {
        return games
    }

    override fun addGame(game: GameRequest): Game {
        return Game(
            id = UUID.randomUUID().toString(),
            name = game.name,
            price = game.price.toFloat(),
            genre = game.genre
        )
            .also { games.add(it) }
    }

    override fun getGame(id: String): Game? {
        return games.firstOrNull { it.id == id }
    }
}