package com.ezorder.app.data.model

/**
 * A single line in the cart. Name/price are snapshotted at add-time —
 * if a restaurant changes a menu price later, items already in someone's
 * cart shouldn't silently change value under them.
 */
data class CartItem(
    val foodItemId: String,
    val restaurantId: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val isVegetarian: Boolean,
    val specialInstructions: String = ""
)

/**
 * restaurantId is nullable because an empty cart belongs to no restaurant.
 * Once the first item is added, every subsequent item must match that
 * restaurantId — enforced in the repository, not here (this is just the shape).
 */
data class Cart(
    val restaurantId: String? = null,
    val items: List<CartItem> = emptyList()
) {
    val subtotal: Double
        get() = items.sumOf { it.price * it.quantity }

    val itemCount: Int
        get() = items.sumOf { it.quantity }
}