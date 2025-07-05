package com.eveggies.tujijenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eveggies.tujijenge.ui.theme.TujijengeTheme



// Brand Colors
val TujijengeGreen = Color(0xFF084236)

val TujijengeWhite = Color(0xFFFFFFFF)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TujijengeTheme{
                AppNavigation()
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route){
            LoginScreen(navController)
        }
        composable(Screen.Home.route){
            HomeScreen()
        }


    }
}
fun HomeScreen() {
    TODO("Not yet implemented")
}



@Composable
fun LoginScreen(navController: NavHostController) {

    var phoneNumber by remember {
        mutableStateOf("")
    }
    val focusRequesters = remember { List(4) { FocusRequester() } }
    var newPin by remember {
        mutableStateOf("")

    }
    val nunito = FontFamily(
        Font(R.font.nunito)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tujijengelogo),
                contentDescription = "Tujijenge Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 1.dp),

                tint = Color.Unspecified
            )
            Text(
                text = "Login",
                fontSize = 32.sp,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(700)),
                modifier = Modifier.padding(bottom = 50.dp),
                color = Color(0xFF084236)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Phone Number:",
                    fontSize = 16.sp,
                    color = Color(0xFF084236),
                    modifier = Modifier.padding(start = 40.dp, bottom = 5.dp),
                    fontWeight = FontWeight(500)

                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { newValue ->
                    phoneNumber = newValue
                },
                placeholder = {
                    Text(
                        text = "Enter Phone Number",
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },

                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth()
//                    .defaultMinSize(minHeight = 50.dp)
                    .height(50.dp)
                    .padding(horizontal = 40.dp),

                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF084236), unfocusedBorderColor = Color(0xFFC2CA83)

                ),
            )

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "PIN:",
                    fontSize = 16.sp,
                    color = Color(0xFF084236),
                    modifier = Modifier.padding(start = 40.dp),
                    fontWeight = FontWeight(500)

                )

            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        PinInputField(
            pin = newPin,
            onPinChange = { newPin = it },
            focusRequesters = focusRequesters
        )
        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(55.dp).align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TujijengeGreen,
                contentColor = TujijengeWhite
            )
        )
        {
            Text(
                text = "Login",
                fontSize = 25.sp,
                fontFamily = nunito,
                fontWeight = FontWeight.Bold

            )
        }
        Spacer(modifier = Modifier.height(10.dp))



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign up")
                    }
                },
                fontSize = 12.sp,
                color = Color(0xFF084236),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 90.dp),
            )


        }
        Spacer(modifier = Modifier.weight(0.2f))


    }
}





@Composable
fun PinInputField(
    pin:String,
    onPinChange:(String)-> Unit,
    focusRequesters: List<FocusRequester>
){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(start = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ){
        repeat(4){ index ->
            OutlinedTextField(
                value = if (index < pin.length) pin[index].toString()else "",
                onValueChange = {newDigit ->
                    if(newDigit.length<=1){
                        val updatedPin =if(index<pin.length){
                            pin.substring(0, index) + newDigit + pin.substring(index + 1)
                        } else{
                            pin+newDigit
                        }
                        onPinChange(updatedPin.take(4))
                        if(newDigit.isNotEmpty()&&index<3){
                            focusRequesters[index+1].requestFocus()
                        } else if (newDigit.isEmpty()&&index>0){
                            focusRequesters[index-1].requestFocus()
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF084236),unfocusedBorderColor = Color(0xFFC2CA83)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .focusRequester(focusRequesters[index]))

        }}}







