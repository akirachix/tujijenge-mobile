package com.eveggies.tujijenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.eveggies.tujijenge.ui.theme.TujijengeGreen
import com.eveggies.tujijenge.ui.theme.TujijengeLightGreen
import com.eveggies.tujijenge.ui.theme.TujijengeWhite

@Composable
fun DisclaimerScreen(
    onContinue: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
     var inStall by remember { mutableStateOf<Boolean?>(null) }
    val nunito = try {
        FontFamily(Font(R.font.nunito))
    } catch (e: Exception) {
        FontFamily.Default
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TujijengeGreen.copy(alpha = 0.4F))
            .padding(20.dp),
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
        Spacer(modifier = Modifier.height(14.dp))
        runCatching {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Tujijenge brand",
                modifier = Modifier.width(220.dp),
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .border(2.dp, color = TujijengeGreen, shape = RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .background(TujijengeWhite, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Disclaimer!",
                    color = TujijengeGreen,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunito,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Please make sure you are in your stall when you fill the following step for suppliers and training purposes.",
                    color = TujijengeGreen,
                    fontSize = 16.sp,
                    fontFamily = nunito,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = inStall == true,
                            onCheckedChange = { inStall = true },
                            colors = CheckboxDefaults.colors(checkedColor = TujijengeGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("I am in my stall.", color = TujijengeGreen, fontSize = 16.sp)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = inStall == false,
                            onCheckedChange = { inStall = false },
                            colors = CheckboxDefaults.colors(checkedColor = TujijengeGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("No, I am not in my stall.", color = TujijengeGreen, fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { inStall?.let { onContinue(it) } },
                    enabled = inStall != null,
                    modifier = Modifier.width(170.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TujijengeGreen,
                        contentColor = TujijengeWhite,
                        disabledContainerColor = TujijengeLightGreen,
                        disabledContentColor = TujijengeGreen
                    )
                ) {
                    Text("Continue", color = TujijengeWhite, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisclaimerScreenPreview() {
    DisclaimerScreen(
        onContinue = {},
        onBackClick = {}
    )
}