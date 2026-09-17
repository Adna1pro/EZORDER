package com.ezorder.app.data.repository

import com.ezorder.app.data.model.FoodItem

interface FoodItemRepository {
    suspend fun getItemsForRestaurant(restaurantId: String): List<FoodItem>
    suspend fun getItemById(id: String): FoodItem?
}

class MockFoodItemRepository : FoodItemRepository {
    private val items = listOf(
        FoodItem("f1", "r1", "Masala Dosa", "Crispy rice crepe with spiced potato filling", 90.0,
            "https://images.unsplash.com/photo-1630383249896-424e482df921", true, true, true, "Breakfast"),
        FoodItem("f2", "r1", "Chicken Biryani", "Fragrant basmati rice with slow-cooked chicken", 220.0,
            "https://images.unsplash.com/photo-1589302168068-964664d93dc0", false, true, true, "Main Course"),
        FoodItem("f3", "r1", "Filter Coffee", "South Indian style decoction coffee", 40.0,
            "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085", true, false, false, "Beverages"),

        FoodItem("f4", "r2", "Paneer Butter Masala", "Cottage cheese in a rich tomato gravy", 210.0,
            "https://images.unsplash.com/photo-1546833999-b9f581a1996d", true, true, true, "Main Course"),
        FoodItem("f5", "r2", "Butter Naan", "Soft leavened flatbread with butter", 45.0,
            "https://images.unsplash.com/photo-1601050690597-df0568f70950", true, true, false, "Breads"),

        FoodItem("f6", "r3", "Fish Curry", "Fresh catch in a coastal coconut curry", 280.0,
            "https://images.unsplash.com/photo-1626200419199-391ae4be7a41", false, true, true, "Main Course"),

        FoodItem("f7", "r4", "Tandoori Chicken", "Smoky charcoal-grilled marinated chicken", 320.0,
            "https://images.unsplash.com/photo-1610057099431-d73a1c9d3f9d", false, true, true, "Starters"),

        FoodItem("f8", "r5", "Rava Dosa", "Crisp semolina crepe, served with chutney", 100.0,
            "https://images.unsplash.com/photo-1668236543090-82eba5ee5976", true, true, false, "Breakfast")
    )

    override suspend fun getItemsForRestaurant(restaurantId: String): List<FoodItem> =
        items.filter { it.restaurantId == restaurantId }

    override suspend fun getItemById(id: String): FoodItem? =
        items.find { it.id == id }
}