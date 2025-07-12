package com.eveggies.tujijenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eveggies.tujijenge.R
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent

@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Tujijenge Logo",
                modifier = Modifier.size(450.dp)
            )
            Image(
                painter = painterResource(id=R.drawable.logo2),
                contentDescription = "Tujijenge text logo",
                modifier = Modifier.size(300.dp)

            )

        }
    }
    LaunchedEffect(true) {
        delay(2000L) //
        navController.navigate("Onboarding1") {
            popUpTo("splash") { inclusive = true }
        }
    }
}