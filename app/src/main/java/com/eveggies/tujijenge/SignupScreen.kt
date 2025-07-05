package com.eveggies.signup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eveggies.tujijenge.R
import com.eveggies.tujijenge.ui.theme.TujijengeFont
import com.eveggies.tujijenge.ui.theme.TujijengeGreen
import com.eveggies.tujijenge.ui.theme.TujijengeLightGreen
import com.eveggies.tujijenge.ui.theme.TujijengeWhite

@Composable
fun SignupScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        // Back Arrow
        IconButton(
            onClick = { navController.navigate("Onboarding1") },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_back_24),
                contentDescription = "Back arrow",
                tint = TujijengeGreen
            )
        }
        // Logo & Title
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tujijengelogo),
                contentDescription = "Tujijenge Logo",
                modifier = Modifier.size(250.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to Tujijenge",
                style = MaterialTheme.typography.titleMedium,
                color = TujijengeGreen,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = TujijengeFont
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        // Input Fields
        listOf(
            "First Name:" to firstName to { it: TextFieldValue -> firstName = it },
            "Last Name:" to lastName to { it: TextFieldValue -> lastName = it },
            "Phone number:" to phoneNumber to { it: TextFieldValue -> phoneNumber = it }
        ).forEach { (labelPair, valuePair) ->
            val label = labelPair.first
            val value = labelPair.second
            val onChange = valuePair
            Column(modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterHorizontally)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = TujijengeGreen,
                    fontFamily = TujijengeFont
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = value,
                    onValueChange = onChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TujijengeLightGreen, RoundedCornerShape(8.dp)),
                    placeholder = {
                        Text("Enter ${label.removeSuffix(":").lowercase()}",
                            fontFamily = TujijengeFont,
                            fontWeight = FontWeight.Thin
                        )

                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = TujijengeWhite,
                        unfocusedContainerColor = TujijengeWhite,
                        focusedBorderColor = TujijengeLightGreen,
                        unfocusedBorderColor = TujijengeLightGreen,
                        focusedTextColor = TujijengeGreen,
                        unfocusedTextColor = TujijengeGreen
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        // Continue Button
        Button(
            onClick = {
                if (firstName.text.isNotBlank() && lastName.text.isNotBlank() && phoneNumber.text.isNotBlank()) {
                    navController.navigate("enterPinScreen")
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(60.dp).align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TujijengeGreen,
                contentColor = TujijengeWhite
            )
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = TujijengeFont
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Login Text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = TujijengeFont
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodySmall,
                color = TujijengeGreen,
                fontFamily = TujijengeFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(navController = rememberNavController())
}