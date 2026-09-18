package com.ezorder.app.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import com.ezorder.app.EZOrderApplication
import com.ezorder.app.ui.customer.cart.CartScreen
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.padding
import com.ezorder.app.ui.customer.menu.MenuScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ezorder.app.ui.components.CustomerTab
import com.ezorder.app.ui.components.EZBottomNav
import com.ezorder.app.ui.customer.home.HomeScreen
import com.ezorder.app.ui.customer.orders.OrdersScreen
import com.ezorder.app.ui.customer.reservation.TableSelectionScreen
import com.ezorder.app.ui.customer.profile.ProfileScreen
import com.ezorder.app.ui.customer.reservation.ReservationsScreen
import com.ezorder.app.ui.customer.restaurant.RestaurantDetailScreen
import com.ezorder.app.ui.customer.search.SearchScreen

private const val TABLE_SELECTION_ROUTE = "table_selection/{restaurantId}"
private const val RESTAURANT_DETAIL_ROUTE = "restaurant_detail/{restaurantId}"
private const val MENU_ROUTE = "menu/{restaurantId}"

@Composable
fun CustomerNavHost(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val cartRepository = (LocalContext.current.applicationContext as EZOrderApplication).cartRepository
    val cart by cartRepository.cart.collectAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Detail screens (with arguments baked into the route) won't match
    // any CustomerTab by name — bottomBarVisible controls whether the
    // nav bar shows at all on those screens, rather than misleadingly
    // highlighting Home.
    val selectedTab = CustomerTab.entries.find { it.name == currentRoute }
    val bottomBarVisible = selectedTab != null

    Scaffold(
        bottomBar = {
            if (bottomBarVisible) {
                EZBottomNav(
                    selectedTab = selectedTab ?: CustomerTab.Home,
                    onTabSelected = { tab ->
                        navController.navigate(tab.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CustomerTab.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(CustomerTab.Home.name) {
                HomeScreen(
                    onRestaurantClick = { restaurantId ->
                        navController.navigate("restaurant_detail/$restaurantId")
                    }
                )
            }
            composable(CustomerTab.Search.name) { SearchScreen() }
            composable(CustomerTab.Reservations.name) { ReservationsScreen() }
            composable(CustomerTab.Orders.name) { OrdersScreen() }
            composable(CustomerTab.Profile.name) { ProfileScreen() }

            composable(
                route = RESTAURANT_DETAIL_ROUTE,
                arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
            ) { entry ->
                val restaurantId = entry.arguments?.getString("restaurantId") ?: return@composable
                RestaurantDetailScreen(
                    restaurantId = restaurantId,
                    onViewMenuClick = { id -> navController.navigate("menu/$id") },
                    onReserveTableClick = { id -> navController.navigate("table_selection/$id") }
                )
            }
            composable("cart") {
                CartScreen(cartRepository = cartRepository)
            }
            composable(
                route = TABLE_SELECTION_ROUTE,
                arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
            ) { entry ->
                val restaurantId = entry.arguments?.getString("restaurantId") ?: return@composable
                TableSelectionScreen(restaurantId = restaurantId)
            }
            composable(
                route = MENU_ROUTE,
                arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
            ) { entry ->
                val restaurantId = entry.arguments?.getString("restaurantId") ?: return@composable
                MenuScreen(
                    restaurantId = restaurantId,
                    cartRepository = cartRepository,
                    onCartClick = { navController.navigate("cart") }
                )
            }
        }
    }
}