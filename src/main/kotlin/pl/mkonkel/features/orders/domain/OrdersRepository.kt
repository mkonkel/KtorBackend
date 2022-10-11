package pl.mkonkel.features.orders.domain

import pl.mkonkel.features.orders.data.Order
import pl.mkonkel.features.orders.data.OrderRequest

interface OrdersRepository {
    fun getOrders(): List<Order>
    fun addOrder(orderRequest: OrderRequest): Order
    fun getOrder(orderId: String): Order?
    fun delete(orderId: String)
}