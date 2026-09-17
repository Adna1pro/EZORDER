package com.ezorder.app.data.repository

import com.ezorder.app.data.model.Cart
import com.ezorder.app.data.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Every other repository here returns fixed catalog data (restaurants,
 * menu items). The cart is different — it's live session state that
 * changes as the user taps "+" on FoodCards, so it's exposed as a
 * StateFlow instead of a plain suspend-fetch. ViewModels will collect
 * this in Phase 3 to keep the UI in sync automatically.
 */
interface CartRepository {
    val cart: StateFlow<Cart>
    fun addItem(item: CartItem)
    fun removeItem(foodItemId: String)
    fun clearCart()
}

class MockCartRepository : CartRepository {
    private val _cart = MutableStateFlow(Cart())
    override val cart: StateFlow<Cart> = _cart

    override fun addItem(item: CartItem) {
        val current = _cart.value
        // Enforce single-restaurant cart per spec: adding from a different
        // restaurant replaces the cart rather than mixing orders.
        if (current.restaurantId != null && current.restaurantId != item.restaurantId) {
            _cart.value = Cart(restaurantId = item.restaurantId, items = listOf(item))
            return
        }
        val existing = current.items.find { it.foodItemId == item.foodItemId }
        val updatedItems = if (existing != null) {
            current.items.map {
                if (it.foodItemId == item.foodItemId) it.copy(quantity = it.quantity + item.quantity) else it
            }
        } else {
            current.items + item
        }
        _cart.value = Cart(restaurantId = item.restaurantId, items = updatedItems)
    }

    override fun removeItem(foodItemId: String) {
        val current = _cart.value
        val updatedItems = current.items.filterNot { it.foodItemId == foodItemId }
        _cart.value = if (updatedItems.isEmpty()) Cart() else current.copy(items = updatedItems)
    }

    override fun clearCart() {
        _cart.value = Cart()
    }
}