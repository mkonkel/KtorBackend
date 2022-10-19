package pl.mkonkel.features.orders.presentation

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import pl.mkonkel.features.orders.OrdersFeature
import pl.mkonkel.features.orders.data.OrderRequest
import pl.mkonkel.features.orders.domain.OrdersRepository

fun Route.ordersRouting() {
    val repo: OrdersRepository = OrdersFeature.repository

    get<OrdersResources> {
        val orders = repo.getOrders()
        call.respond(orders)
    }

    get<OrdersResources.Id> { request ->
        val order = repo.getOrder(request.id)

        order?.let { call.respond(it) } ?: call.respondText(
            status = HttpStatusCode.BadRequest,
            text = "No such order! OrderId: ${request.id}"
        )
    }

    get<OrdersResources.Id.Address> { request ->
        val address = repo.getOrder(request.parent.id)?.address
        address?.let { call.respond(it) } ?: call.respondText("No such order! OrderId: ${request.parent.id}")
    }

    post<OrdersResources> {
        val orderRequest = call.receive<OrderRequest>()
        val newOrder = repo.addOrder(orderRequest)
        call.respond(newOrder)
    }

    delete<OrdersResources.Id> { request ->
        try {
            repo.delete(request.id)
            call.respond(
                status = HttpStatusCode.NoContent,
                message = "Order deleted"
            )
        } catch (e: Exception) {
            call.respondText(
                status = HttpStatusCode.BadRequest,
                text = "No such order! OrderId: ${request.id}"
            )
        }
    }
}