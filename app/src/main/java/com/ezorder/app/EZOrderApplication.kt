package com.ezorder.app

import android.app.Application
import com.ezorder.app.data.repository.CartRepository
import com.ezorder.app.data.repository.MockCartRepository

/**
 * EZORDER application entry point.
 *
 * Holds the single shared CartRepository instance for the whole app.
 * Cart holds live session state (unlike the read-only catalog repos
 * for restaurants/food items), so every screen that touches the cart
 * must share this one instance — otherwise MenuScreen and a future
 * CartScreen would each see a different, out-of-sync cart.
 */
class EZOrderApplication : Application() {
    val cartRepository: CartRepository by lazy { MockCartRepository() }
}