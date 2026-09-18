package com.ezorder.app.ui.customer.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezorder.app.data.model.Restaurant
import com.ezorder.app.data.repository.MockRestaurantRepository
import com.ezorder.app.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantDetailViewModel(
    private val restaurantId: String,
    private val restaurantRepository: RestaurantRepository = MockRestaurantRepository()
) : ViewModel() {

    private val _restaurant = MutableStateFlow<Restaurant?>(null)
    val restaurant: StateFlow<Restaurant?> = _restaurant.asStateFlow()

    init {
        viewModelScope.launch {
            _restaurant.value = restaurantRepository.getRestaurantById(restaurantId)
        }
    }
}