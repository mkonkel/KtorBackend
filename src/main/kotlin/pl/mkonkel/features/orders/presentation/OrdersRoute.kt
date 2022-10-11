package pl.mkonkel.features.orders.presentation

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.locations.delete
import io.ktor.server.locations.get
import io.ktor.server.locations.post
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import pl.mkonkel.features.games.data.GameRequest
import pl.mkonkel.features.orders.OrdersFeature
import pl.mkonkel.features.orders.data.OrderRequest
import pl.mkonkel.features.orders.domain.OrdersRepository

fun Route.ordersRouting() {
    val repo: OrdersRepository = OrdersFeature.repository

    get<OrdersEndpoints.List> {
        val orders = repo.getOrders()
        call.respond(orders)
    }

    get<OrdersEndpoints.Get> { request ->
        val order = repo.getOrder(request.id)

        order?.let { call.respond(it) } ?: call.respondText(
            status = HttpStatusCode.BadRequest,
            text = "No such order! OrderId: ${request.id}"
        )
    }

    get<OrdersEndpoints.Address> { request ->
        val address = repo.getOrder(request.id)?.address
        address?.let { call.respond(it) } ?: call.respondText("No such order! OrderId: ${request.id}")
    }

    post<OrdersEndpoints.Create> {
        val orderRequest = call.receive<OrderRequest>()
        val newOrder = repo.addOrder(orderRequest)
        call.respond(newOrder)
    }

    delete<OrdersEndpoints.Delete> { request ->
        repo.delete(request.id)
        call.respond(
            status = HttpStatusCode.Created,
            message = "Order created"
        )
    }
}