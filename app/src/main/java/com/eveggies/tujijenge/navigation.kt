package com.eveggies.tujijenge

import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "enter_pin") {
        composable("enter_pin") {
            EnterPinScreen(
                onBackClick = { /* Handle if needed */ },
                onPinSuccess = { navController.navigate("disclaimer") }
            )
        }
        composable("disclaimer") {
            DisclaimerScreen(
                onContinue = { inStall ->
                    // Always go to community screen, passing the Boolean value as a string
                    navController.navigate("community?showLocationDialog=${inStall}")
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            "community?showLocationDialog={showLocationDialog}",
            arguments = listOf(
                navArgument("showLocationDialog") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->
            val showLocationDialog = backStackEntry.arguments?.getBoolean("showLocationDialog") ?: false
            var showDialog by remember { mutableStateOf(showLocationDialog) }

            val communities = listOf(
                Community("1", "Community A", 10, "500m away", "Karen Korongo Road"),
                Community("2", "Community B", 10, "500m away", "Nairobi Kenya"),
                Community("3", "Community C", 10, "500m away", "Karen Korongo Road")
            )

            CommunityScreen(
                communities = communities,
                onBackClick = { navController.popBackStack() }
            )

            if (showDialog) {
                AllowLocationScreen(
                    onAllow = { showDialog = false },
                    onDeny = { showDialog = false }
                )
            }
        }
    }
}