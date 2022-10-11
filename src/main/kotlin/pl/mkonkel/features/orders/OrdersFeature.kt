package pl.mkonkel.features.orders

import pl.mkonkel.features.orders.domain.OrdersRepositoryImpl
import pl.mkonkel.features.games.GamesFeature
import pl.mkonkel.features.orders.domain.OrdersRepository

object OrdersFeature {
    val repository: OrdersRepository = OrdersRepositoryImpl(GamesFeature.repository)
}