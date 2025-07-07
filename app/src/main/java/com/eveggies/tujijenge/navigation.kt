package com.eveggies.tujijenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eveggies.signup.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "Onboarding1") {
        composable("Onboarding1") { FirstOnboarding(navController) }
        composable("Onboarding2") { SecondOnboarding(navController) }
        composable("Onboarding3") { ThirdOnboarding(navController) }
        composable ("Onboarding4"){FourthOnboarding(navController)}
        composable("Signup") { SignupScreen(navController) } // Replace with your actual signup screen
    }
}