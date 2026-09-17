package com.ezorder.app.data.repository

import com.ezorder.app.data.model.Table
import com.ezorder.app.data.model.TableStatus

interface TableRepository {
    suspend fun getTablesForRestaurant(restaurantId: String): List<Table>
}

class MockTableRepository : TableRepository {
    private val tables = listOf(
        Table("t1", "r1", 1, 2, TableStatus.AVAILABLE),
        Table("t2", "r1", 2, 4, TableStatus.RESERVED),
        Table("t3", "r1", 3, 4, TableStatus.AVAILABLE),
        Table("t4", "r2", 1, 6, TableStatus.AVAILABLE),
        Table("t5", "r3", 1, 2, TableStatus.OCCUPIED)
    )

    override suspend fun getTablesForRestaurant(restaurantId: String): List<Table> =
        tables.filter { it.restaurantId == restaurantId }
}