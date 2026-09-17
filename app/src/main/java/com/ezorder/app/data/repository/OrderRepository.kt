package com.ezorder.app.data.repository

import com.ezorder.app.data.model.Order

interface OrderRepository {
    suspend fun getOrdersForUser(userId: String): List<Order>
    suspend fun placeOrder(order: Order): Order
}

class MockOrderRepository : OrderRepository {
    private val orders = mutableListOf<Order>()

    override suspend fun getOrdersForUser(userId: String): List<Order> =
        orders.filter { it.userId == userId }

    override suspend fun placeOrder(order: Order): Order {
        orders.add(order)
        return order
    }
}