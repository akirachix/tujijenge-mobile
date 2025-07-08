package com.eveggies.tujijenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController


@Composable
fun ResetPinScreen() {
    var newPin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val newPinFocusRequesters = remember { List(4) { FocusRequester() } }
    val confirmPinFocusRequesters = remember { List(4) { FocusRequester() } }
    val nunito = FontFamily(
        Font(R.font.nunito)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_back_24),
                contentDescription = "Back arrow",
                tint = Color(0xFF084236)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {


            Image(
                painter = painterResource(id = R.drawable.tujijenge),
                contentDescription = "Tujijenge brand",
                modifier = Modifier
                    .padding(0.dp, 50.dp)
                    .size(width = 250.dp, height = 200.dp)
                    .fillMaxHeight(0.3F),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Reset PIN",
                fontFamily = nunito,
                fontSize = 32.sp,
                color = Color(0xFF084236),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "New PIN:",
                    fontSize = 16.sp,
                    color = Color(0xFF084236),
                    modifier = Modifier.padding(start = 70.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            PinInputField(
                pin = newPin,
                onPinChange = { newPin = it },
                focusRequesters = newPinFocusRequesters
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(

                    text = "Confirm PIN:",
                    fontSize = 16.sp,
                    color = Color(0xFF084236),
                    modifier = Modifier.padding(start = 70.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            PinInputField(
                pin = confirmPin,
                onPinChange = { confirmPin = it },
                focusRequesters = confirmPinFocusRequesters
            )

            if (showError && newPin.isNotEmpty() && confirmPin.isNotEmpty() && newPin != confirmPin) {
                Text(
                    fontFamily = nunito,
                    text = "PINs do not match!",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    if (newPin == confirmPin && newPin.length == 4) {

                        showError = false

                    } else {
                        showError = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF084236),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFC2CA83),
                    disabledContentColor = Color(0xFF084236)
                ),
                enabled = newPin.length == 4 && confirmPin.length == 4
            ) {
                Text(
                    text = "Reset",
                    fontFamily = nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
        }
    }
}
@Composable
fun PinInputField(
    pin: String,
    onPinChange: (String) -> Unit,
    focusRequesters: List<FocusRequester>
)
{
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(4) { index ->
            var isFocused by remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = if (isFocused) Color(0xFF084236) else Color(0xFFC2CA83)
                    )
            )
            {
                BasicTextField(
                    value = if (index < pin.length) pin[index].toString() else "",
                    onValueChange = { value ->
                        if (value.length <= 1 && value.all { it.isDigit() }) {
                            val updatedPin = StringBuilder(pin).apply {
                                if (value.isNotEmpty()) {
                                    if (index < pin.length) setCharAt(index, value[0])
                                    else append(value)
                                } else if (pin.isNotEmpty() && index < pin.length) {
                                    deleteCharAt(index)
                                }
                            }.toString().take(4)
                            onPinChange(updatedPin)

                            if (value.isNotEmpty() && index < 3) {
                                focusRequesters[index + 1].requestFocus()
                            } else if (value.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .width(48.dp)
                        .height(56.dp)
                        .padding(top = 8.dp)
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        color = Color(0xFF084236)
                    ),
                    decorationBox = { innerTextField -> innerTextField() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterPinScreenPreview1() {
    ResetPinScreen()
}