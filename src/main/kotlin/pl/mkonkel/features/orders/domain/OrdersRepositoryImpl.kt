package pl.mkonkel.features.orders.domain

import kotlinx.datetime.Clock
import pl.mkonkel.features.games.data.Game
import pl.mkonkel.features.games.domain.GamesRepository
import pl.mkonkel.features.orders.data.Order
import pl.mkonkel.features.orders.data.OrderRequest
import java.util.*

class OrdersRepositoryImpl(private val gamesRepository: GamesRepository) : OrdersRepository {
    private val orders: MutableList<Order> = mutableListOf()

    override fun getOrders(): List<Order> {
        return orders
    }

    override fun addOrder(orderRequest: OrderRequest): Order {
        return orderRequest.toOrder() { gamesRepository.getGame(it) }
    }

    override fun getOrder(orderId: String): Order? {
        return orders.firstOrNull { it.id == orderId }
    }

    private fun OrderRequest.toOrder(gamesProvider: (String) -> Game?): Order {
        val games = this.gameIds.map { requireNotNull(gamesProvider(it)) }

        return Order(
            id = UUID.randomUUID().toString(),
            order_date = Clock.System.now().epochSeconds.toString(),
            games = games,
            price = games.map { it.price }.sum(),
            address = this.address
        ).also {
            orders.add(it)
        }
    }

    override fun delete(orderId: String) {
        val order = orders.first { it.id == orderId }
        orders.remove(order)

    }
}