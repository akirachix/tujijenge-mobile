
package com.eveggies.tujijenge


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    // Add other screens here
}
