package com.eveggies.tujijenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
val TujijengeLightGreen = Color(0xFFC0C882)
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

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val imageUrl: Int? = null )

@Composable
fun ProductLazyRow(products: List<Product>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp), // Padding at the start and end of the list
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between items
    ) {
        items(products) { product -> // The 'items' extension makes it easy to iterate
            ProductCard(product = product)
        }

    }
}


    val taimbaProducts = listOf(
        Product("p1", "Fresh Spinach", "Ksh 60/bunch", R.drawable.tujijengelogo),
        Product("p2", "Ripe Bananas", "Ksh 100/comb", R.drawable.tujijengelogo),
        Product("p3", "Red Apples", "Ksh 200/kg", R.drawable.tujijengelogo),
        Product("p4", "Potatoes", "Ksh 180/2kg bag", R.drawable.tujijengelogo)
        // Add more products
    )

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(150.dp)
            .height(250.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Product Image (Optional)
            if (product.imageUrl != null) {
                Image(
                    painter = painterResource(id = product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreen() {
    val nunito = FontFamily(
        Font(R.font.nunito)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp)
            .padding(horizontal = 24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MyCard(
            title = "My Card",
            description = "This is a sample card"
        ){}
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Taimba Products",
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
            modifier = Modifier.padding(bottom = 10.dp),
            color = Color(0xFF084236))
        Text(text = "Products",
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
            modifier = Modifier
            )
        Spacer(modifier = Modifier.height(16.dp))
        ProductLazyRow(products = taimbaProducts)



    }
}

@Composable
fun MyCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),

        shape = MaterialTheme.shapes.medium, // Use a predefined shape from your theme
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFDBE1AD))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
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
                .height(55.dp)
                .align(Alignment.CenterHorizontally),
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
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







