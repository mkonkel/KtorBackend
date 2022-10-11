package pl.mkonkel.features.orders.presentation

import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Location

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("orders")
class OrdersEndpoints {
    @Location("/{id}")
    data class Get(val id: String)

    @Location("/{id}")
    data class Delete(val id: String)

    @Location("")
    object Create

    @Location("")
    object List

    @Location("{id}/address")
    data class Address(val id: String)
}