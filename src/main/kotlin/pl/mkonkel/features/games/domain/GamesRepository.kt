package pl.mkonkel.features.games.domain

import pl.mkonkel.features.games.data.Game
import pl.mkonkel.features.games.data.GameRequest

interface GamesRepository {
    fun getGames(): List<Game>
    fun addGame(game: GameRequest): Game
    fun getGame(id: String): Game?
}