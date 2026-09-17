package com.ezorder.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.ezorder.app.ui.theme.EZORDERTheme

/**
 * The five top-level destinations on the customer side of EZORDER.
 * The Restaurant Owner app gets its own separate tab set later.
 */
enum class CustomerTab(
    val label: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Filled.Home),
    Search("Search", Icons.Filled.Search),
    Reservations("Reserve", Icons.Filled.DateRange),
    Orders("Orders", Icons.Filled.ShoppingCart),
    Profile("Profile", Icons.Filled.Person)
}

/**
 * Stateless bottom navigation bar.
 *
 * Knows nothing about routes or NavController — it only reports which tab
 * was tapped. Navigation wiring happens in a later phase.
 *
 * @param badgeCounts optional map of tab -> count, used for things like
 *                    items currently in the cart. Zero or missing = no badge.
 */
@Composable
fun EZBottomNav(
    selectedTab: CustomerTab,
    onTabSelected: (CustomerTab) -> Unit,
    modifier: Modifier = Modifier,
    badgeCounts: Map<CustomerTab, Int> = emptyMap()
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        CustomerTab.entries.forEach { tab ->
            val count = badgeCounts[tab] ?: 0

            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = {
                    if (count > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError
                                ) {
                                    Text(if (count > 9) "9+" else count.toString())
                                }
                            }
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        }
                    } else {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )
                    }
                },
                label = {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EZBottomNavPreview() {
    EZORDERTheme {
        var selected by remember { mutableStateOf(CustomerTab.Home) }

        EZBottomNav(
            selectedTab = selected,
            onTabSelected = { selected = it },
            badgeCounts = mapOf(CustomerTab.Orders to 3)
        )
    }
}