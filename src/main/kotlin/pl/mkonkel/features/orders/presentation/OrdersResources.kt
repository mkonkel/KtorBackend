package pl.mkonkel.features.orders.presentation

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import pl.mkonkel.features.orders.data.Order

@Serializable
@Resource("/orders")
class OrdersResources {
    @Serializable
    @Resource("{id}")
    class Id(val parent: OrdersResources = OrdersResources(), val id: String) {
        @Serializable
        @Resource("edit")
        class Edit(val parent: Id, val name: String)

        @Serializable
        @Resource("address")
        class Address(val parent: Id)
    }
}