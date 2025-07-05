package com.eveggies.tujijenge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.eveggies.tujijenge.ui.theme.TujijengeFont
import com.eveggies.tujijenge.ui.theme.TujijengeGreen
import com.eveggies.tujijenge.ui.theme.TujijengeLightGreen
import com.eveggies.tujijenge.ui.theme.TujijengeTheme
import com.eveggies.tujijenge.ui.theme.TujijengeWhite
@Composable
fun FirstOnboarding(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(0) } // Track current page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextButton(
            onClick = {navController.navigate("Onboarding2") },
            modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
        ) {
            Text(text = "Skip", color = TujijengeGreen, fontWeight = FontWeight.Normal)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.firstonboarding),
                contentDescription = "Community image",
                modifier = Modifier.size(300.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Join a community",
                style = MaterialTheme.typography.titleLarge,
                color = TujijengeGreen,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Order in bulk and have the opportunity of getting products supplied to you.",
                style = MaterialTheme.typography.bodyLarge,
                color = TujijengeGreen,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont,
                modifier = Modifier.widthIn(max=270.dp)
            )
            Spacer(modifier = Modifier.height(150.dp))
            // Dot Indicator
            DotIndicator(currentPage = 1, totalDots = 4)
            Spacer(modifier = Modifier.height(32.dp))
            // Next Button
            Button(
                onClick = {
                    currentPage = (currentPage + 1) % 3
                    navController.navigate("Onboarding2")
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TujijengeGreen,
                    contentColor = TujijengeWhite
                )
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = TujijengeFont
                )
            }
        }
    }
}
@Composable
fun DotIndicator(currentPage: Int, totalDots: Int) {
    val dotSize = 16.dp
    val spacing = 6.dp
    val mergedDotWidth = (dotSize * currentPage) + (spacing * (currentPage - 1)).coerceAtLeast(0.dp)
    Row(
        modifier = Modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Merged green ellipse for completed steps
        if (currentPage > 0) {
            Box(
                modifier = Modifier
                    .width(mergedDotWidth)
                    .height(dotSize)
                    .clip(RoundedCornerShape(50)) // Ellipse shape
                    .background(TujijengeGreen)
            )
            Spacer(modifier = Modifier.width(spacing))
        }
        // Unmerged remaining dots (light green)
        for (i in currentPage until totalDots) {
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(TujijengeLightGreen)
            )
            if (i < totalDots - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}








@Composable
fun SecondOnboarding(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(0) } // Track current page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextButton(
            onClick = { navController.navigate("Onboarding3")},
            modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
        ) {
            Text(text = "Skip", color = TujijengeGreen, fontWeight = FontWeight.Normal)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.secondonboarding),
                contentDescription = "Verification and vegetables image",
                modifier = Modifier.size(300.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Get verified from GAIN",
                style = MaterialTheme.typography.titleLarge,
                color = TujijengeGreen,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Training session with GAIN (Global Alliance for Improved Nutrition) to improve your hygiene and increase customers.",
                style = MaterialTheme.typography.bodyLarge,
                color = TujijengeGreen,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont,
                modifier = Modifier.widthIn(max=272.dp)
            )
            Spacer(modifier = Modifier.height(128.dp))
            // Dot Indicator
            DotIndicator(currentPage = 2, totalDots = 4)
            Spacer(modifier = Modifier.height(32.dp))
            // Next Button
            Button(
                onClick = {
                    currentPage = (currentPage + 1) % 3 // Cycle through pages
                    navController.navigate("Onboarding3") // Navigate to next screen
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TujijengeGreen,
                    contentColor = TujijengeWhite
                )
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = TujijengeFont
                )
            }
        }
    }
}
@Composable
fun ThirdOnboarding(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(0) } // Track current page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextButton(
            onClick = { navController.navigate("Onboarding4")  },
            modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
        ) {
            Text(text = "Skip", color = TujijengeGreen, fontWeight = FontWeight.Normal)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fourthonboarding),
                contentDescription = "Products",
                modifier = Modifier.size(300.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Explore Variety of Products",
                style = MaterialTheme.typography.titleLarge,
                color = TujijengeGreen,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Join a community, order in bulk and have the opportunity of  getting products supplied to you at a discount.",
                style = MaterialTheme.typography.bodyLarge,
                color = TujijengeGreen,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont,
                modifier = Modifier.widthIn(max=270.dp)
            )
            Spacer(modifier = Modifier.height(76.dp))
            // Dot Indicator
            DotIndicator(currentPage = 3, totalDots = 4)
            Spacer(modifier = Modifier.height(32.dp))
            // Next Button
            Button(
                onClick = {
                    currentPage = (currentPage + 1) % 3 // Cycle through pages
                    navController.navigate("Onboarding4") // Navigate to next screen
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TujijengeGreen,
                    contentColor = TujijengeWhite
                )
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = TujijengeFont
                )
            }
        }
    }
}

@Composable
fun FourthOnboarding(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(0) } // Track current page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.thirdonboarding),
                contentDescription = "Inventory management",
                modifier = Modifier.size(300.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Manage your stock",
                style = MaterialTheme.typography.titleLarge,
                color = TujijengeGreen,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Easy way to track your sales and manage your inventory.",
                style = MaterialTheme.typography.bodyLarge,
                color = TujijengeGreen,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont,
                modifier = Modifier.widthIn(max=270.dp)
            )
            Spacer(modifier = Modifier.height(218.dp))
            // Dot Indicator
            DotIndicator(currentPage = 4, totalDots = 4)
            Spacer(modifier = Modifier.height(32.dp))
            // Next Button
            Button(
                onClick = {
                    currentPage = (currentPage + 1) % 3 // Cycle through pages
                    navController.navigate("Signup") // Navigate to next screen
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TujijengeGreen,
                    contentColor = TujijengeWhite
                )
            ) {
                Text(
                    text = "Finish",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = TujijengeFont
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OnboardingFlowPreview() {
    TujijengeTheme {
        val navController = rememberNavController()
        AppNavigation(navController = navController)
    }
}

