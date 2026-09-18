package com.ezorder.app.ui.customer.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ezorder.app.ui.components.EmptyState

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = "Search coming soon",
        message = "You'll be able to search restaurants, dishes, and cuisines here.",
        icon = Icons.Filled.Search,
        modifier = modifier.fillMaxSize()
    )
}