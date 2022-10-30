package pl.mkonkel.features.orders.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.mkonkel.features.games.data.Game

@Serializable
data class Order(
    val id: String,
    val order_date: String,
    val games: List<Game>,
    val price: Float,
    val address: String
)

@Serializable
data class OrderRequest(
    @SerialName("games")
    val gameIds: List<String>,
    val address: String
)