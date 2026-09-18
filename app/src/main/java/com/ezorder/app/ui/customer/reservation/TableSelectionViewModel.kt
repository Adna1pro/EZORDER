package com.ezorder.app.ui.customer.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezorder.app.data.model.Table
import com.ezorder.app.data.model.TableStatus
import com.ezorder.app.data.repository.MockTableRepository
import com.ezorder.app.data.repository.TableRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TableSelectionViewModel(
    private val restaurantId: String,
    private val tableRepository: TableRepository = MockTableRepository()
) : ViewModel() {

    private val _availableTables = MutableStateFlow<List<Table>>(emptyList())
    val availableTables: StateFlow<List<Table>> = _availableTables.asStateFlow()

    init {
        viewModelScope.launch {
            _availableTables.value = tableRepository.getTablesForRestaurant(restaurantId)
                .filter { it.status == TableStatus.AVAILABLE }
        }
    }
}