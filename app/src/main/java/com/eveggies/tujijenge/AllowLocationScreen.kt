package com.eveggies.tujijenge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AllowLocationScreen(
    onAllow: () -> Unit,
    onDeny: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA084236)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF084236), RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(vertical = 32.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "Tujijenge brand",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "Allow Location",
                color = Color(0xFFC0C882),
                fontSize = 32.sp,
                fontFamily = nunito,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.width(300.dp),
                text = "We need your permission to access your location",
                color = Color(0xFFC0C882),
                fontSize = 18.sp,
                fontFamily = nunito,
                textAlign = TextAlign.Center

            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = onAllow,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFFC0C882),)
            )

            {
                Text("Allow Location", color = Color(0xFF084236), fontFamily = nunito, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onDeny,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = TujijengeWhite)
            ) {
                Text("Do Not Allow", color = TujijengeGreen, fontFamily = nunito, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}


@Composable
@Preview

fun AllowLocationScreenPreview() {
    AllowLocationScreen(
        onAllow = {},
        onDeny = {}
    )
}