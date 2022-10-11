package pl.mkonkel.features.orders.presentation

import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Location

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("orders")
object OrdersEndpoints {
    @Location("/{id}")
    data class Get(val id: String, val orders: OrdersEndpoints = OrdersEndpoints)

    @Location("/{id}")
    data class Delete(val id: String, val orders: OrdersEndpoints = OrdersEndpoints)

    @Location("")
    data class Create(val orders: OrdersEndpoints = OrdersEndpoints)

    @Location("")
    data class List(val orders: OrdersEndpoints = OrdersEndpoints)

    @Location("{id}/address")
    data class Address(val id: String, val orders: OrdersEndpoints = OrdersEndpoints)
}