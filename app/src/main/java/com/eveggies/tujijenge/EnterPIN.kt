package com.eveggies.tujijenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.eveggies.tujijenge.ui.theme.TujijengeGreen
import com.eveggies.tujijenge.ui.theme.TujijengeLightGreen
import com.eveggies.tujijenge.ui.theme.TujijengeWhite

val nunito = FontFamily(Font(R.font.nunito))

@Composable
fun EnterPinScreen(
    onBackClick: () -> Unit,
    onPinSuccess: (String) -> Unit = {}
) {
    var newPin by rememberSaveable { mutableStateOf("") }
    var confirmPin by rememberSaveable { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val newPinFocusRequesters = remember { List(4) { FocusRequester() } }
    val confirmPinFocusRequesters = remember { List(4) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TujijengeGreen
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Tujijenge brand",
                modifier = Modifier.size(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "Enter PIN",
            fontSize = 32.sp,
            fontFamily = nunito,
            color = TujijengeGreen,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 73.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "PIN:",
                fontSize = 20.sp,
                color = TujijengeGreen,
                modifier = Modifier.padding(start = 5.dp)
                    .padding(bottom = 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        PinInputField(
            pin = newPin,
            onPinChange = { newPin = it },
            focusRequesters = newPinFocusRequesters,
            focusManager = focusManager,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 73.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Confirm PIN:",
                fontSize = 20.sp,
                color = TujijengeGreen,
                modifier = Modifier.padding(start = 5.dp)
                    .padding(bottom = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        PinInputField(
            pin = confirmPin,
            onPinChange = { confirmPin = it },
            focusRequesters = confirmPinFocusRequesters,
            focusManager = focusManager,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        if (showError && newPin != confirmPin) {
            Text(
                fontFamily = nunito,
                text = "PINs do not match!",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = {
                if (newPin == confirmPin && newPin.length == 4 && newPin.all { it.isDigit() }) {
                    showError = false
                    onPinSuccess(newPin)
                } else {
                    showError = true
                    Toast.makeText(context, "PINs do not match or are invalid!", Toast.LENGTH_SHORT).show()
                    newPinFocusRequesters[0].requestFocus()
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TujijengeGreen,
                contentColor = TujijengeWhite,
                disabledContainerColor = TujijengeLightGreen,
                disabledContentColor = TujijengeGreen
            ),
            enabled = newPin.length == 4 && confirmPin.length == 4
        ) {
            Text(
                text = "Sign up",
                fontFamily = nunito,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PinInputField(
    pin: String,
    onPinChange: (String) -> Unit,
    focusRequesters: List<FocusRequester>,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                var isFocused by remember { mutableStateOf(false) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .border(
                            width = if (isFocused) 2.dp else 1.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = if (isFocused) Color(0xFF084236) else Color(0xFFC0C882)
                        )
                ) {
                    BasicTextField(
                        value = if (index < pin.length) pin.getOrNull(index)?.toString() ?: "" else "",
                        onValueChange = { value ->
                            if (value.length <= 1 && value.all { it.isDigit() }) {
                                val pinChars = pin.toCharArray().toMutableList()
                                if (value.isNotEmpty()) {
                                    if (index < pinChars.size) {
                                        pinChars[index] = value[0]
                                    } else if (pinChars.size < 4) {
                                        pinChars.add(value[0])
                                    }
                                    onPinChange(pinChars.joinToString("").take(4))
                                    if (index < 3) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else {
                                        focusManager.clearFocus()
                                    }
                                } else {
                                    if (pinChars.isNotEmpty() && index < pinChars.size) {
                                        pinChars.removeAt(index)
                                        onPinChange(pinChars.joinToString(""))
                                        if (index > 0) {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                    } else if (pinChars.isNotEmpty() && index == pinChars.size) {
                                        pinChars.removeAt(pinChars.size - 1)
                                        onPinChange(pinChars.joinToString(""))
                                        if (index > 0) {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                    }
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                            .focusRequester(focusRequesters[index])
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            },
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                            lineHeight = 22.sp, // Ensures cursor is vertically centered
                            fontFamily = nunito,
                            color = Color(0xFF194D41)
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) { innerTextField() }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterPinScreenPreview() {
    EnterPinScreen(onBackClick = {})
}