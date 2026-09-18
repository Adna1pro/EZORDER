package com.ezorder.app.ui.customer.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezorder.app.data.model.CartItem
import com.ezorder.app.data.model.FoodItem
import com.ezorder.app.data.repository.CartRepository
import com.ezorder.app.data.repository.FoodItemRepository
import com.ezorder.app.data.repository.MockFoodItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel(
    private val restaurantId: String,
    private val cartRepository: CartRepository,
    private val foodItemRepository: FoodItemRepository = MockFoodItemRepository()
) : ViewModel() {

    private val _items = MutableStateFlow<List<FoodItem>>(emptyList())
    val items: StateFlow<List<FoodItem>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            _items.value = foodItemRepository.getItemsForRestaurant(restaurantId)
        }
    }

    fun addToCart(item: FoodItem) {
        viewModelScope.launch {
            cartRepository.addItem(
                CartItem(
                    foodItemId = item.id,
                    restaurantId = item.restaurantId,
                    name = item.name,
                    price = item.price,
                    quantity = 1,
                    isVegetarian = item.isVegetarian
                )
            )
        }
    }
}