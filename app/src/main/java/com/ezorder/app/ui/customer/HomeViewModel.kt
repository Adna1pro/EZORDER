package com.ezorder.app.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezorder.app.data.model.Restaurant
import com.ezorder.app.data.repository.MockRestaurantRepository
import com.ezorder.app.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Exposes the restaurant list to HomeScreen as observable state.
 * Depends on the RestaurantRepository interface, not the Mock
 * implementation directly below — the constructor default is what
 * makes swapping in a real repository later a one-line change here,
 * with zero changes needed in HomeScreen.
 */
class HomeViewModel(
    private val restaurantRepository: RestaurantRepository = MockRestaurantRepository()
) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            _restaurants.value = restaurantRepository.getAllRestaurants()
        }
    }
}