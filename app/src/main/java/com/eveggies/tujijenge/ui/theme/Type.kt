package com.eveggies.tujijenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font

import com.eveggies.tujijenge.R
val TujijengeFont = FontFamily(
    Font(R.font.nunitoregular, FontWeight.Normal),
    Font(R.font.nunitomedium, FontWeight.Medium),
    Font(R.font.nunitobold, FontWeight.Bold)
)
// Set of Material typography styles to start with

val TujijengeTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = TujijengeFont,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = TujijengeFont,
        fontSize = 40.sp
    ),
    titleMedium = TextStyle(
        fontFamily = TujijengeFont,
     fontSize = 32.sp
     ),
    titleSmall = TextStyle(
        fontFamily = TujijengeFont,
        fontSize = 24.sp
    ),
    labelSmall = TextStyle(
        fontFamily = TujijengeFont,
        fontSize = 12.sp
    )

)