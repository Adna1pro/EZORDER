package com.ezorder.app.ui.customer.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ezorder.app.ui.components.EmptyState

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = "Profile coming soon",
        message = "Account details and settings will live here.",
        icon = Icons.Filled.Person,
        modifier = modifier.fillMaxSize()
    )
}