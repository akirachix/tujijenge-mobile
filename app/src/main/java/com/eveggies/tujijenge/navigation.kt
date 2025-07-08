

package com.eveggies.tujijenge


import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.eveggies.signup.SignupScreen



@Composable
fun AppNavigation(navController: NavHostController, startDestination: String = "Onboarding1") {


    NavHost(navController, startDestination = startDestination) {
        composable("Onboarding1") { FirstOnboarding(navController) }
        composable("Onboarding2") { SecondOnboarding(navController) }
        composable("Onboarding3") { ThirdOnboarding(navController) }
        composable("Onboarding4") { FourthOnboarding(navController) }
        composable("Signup") { SignupScreen(navController) }
        composable("enter_pin") {
            EnterPinScreen(
                onBackClick = { navController.popBackStack() }, // Added popBackStack for consistency
                onPinSuccess = { navController.navigate("disclaimer") }
            )
        }
        composable("disclaimer") {
            DisclaimerScreen(
                onContinue = { install ->
                    navController.navigate("community?showLocationDialog=${install}")
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = "community?showLocationDialog={showLocationDialog}",
            arguments = listOf(
                navArgument("showLocationDialog") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->

            val showLocationDialogArgument = backStackEntry.arguments?.getBoolean("showLocationDialog") ?: false


            var showActualDialog by rememberSaveable { mutableStateOf(showLocationDialogArgument) }

            val communities = listOf(
                Community("1", "Community A", 10, "500m away", "Karen Korongo Road"),
                Community("2", "Community B", 10, "500m away", "Nairobi Kenya"),
                Community("3", "Community C", 10, "500m away", "Karen Korongo Road")
            )

            CommunityScreen(
                communities = communities,
                onBackClick = { navController.popBackStack() }
            )

            if (showActualDialog) {
                AllowLocationScreen(
                    onAllow = { showActualDialog = false },
                    onDeny = { showActualDialog = false }
                )
            }
        }

    }
}
