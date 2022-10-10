package pl.mkonkel.features.games.data

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String,
    val name: String,
    val price: String,
    val genre: String
)

@Serializable
data class GameRequest(
    val name: String,
    val price: String,
    val genre: String
)