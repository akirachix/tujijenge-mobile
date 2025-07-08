package com.eveggies.tujijenge
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*

@Composable
fun AppNavigation() {
    
        }
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eveggies.signup.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination = "enter_pin") {
    val navController = rememberNavController()
     
    NavHost(navController, startDestination = "Onboarding1") {
        composable("Onboarding1") { FirstOnboarding(navController) }
        composable("Onboarding2") { SecondOnboarding(navController) }
        composable("Onboarding3") { ThirdOnboarding(navController) }
        composable ("Onboarding4"){FourthOnboarding(navController)}
        composable("Signup") { SignupScreen(navController) } 
         composable("enter_pin") {
            EnterPinScreen(
                onBackClick = { },
                onPinSuccess = { navController.navigate("disclaimer") }
            )
        }
        composable("disclaimer") {
            DisclaimerScreen(
                onContinue = { inStall ->

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
   
