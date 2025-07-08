package com.eveggies.tujijenge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CommunityCard(community: Community, modifier: Modifier = Modifier) {

    val TujijengeGreen = Color(0xFF084236)
    val TujijengeLightGreen = Color(0xFFC0C882)
    val nunito = FontFamily(Font(R.font.nunito))
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = TujijengeLightGreen,
        shadowElevation = 2.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Community group",
                tint = TujijengeGreen,
                modifier = Modifier.size(30.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 9.dp)
            ) {
                Text(
                    text = community.name,
                    color = TujijengeGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunito
                )
                Text(
                    text = "${community.members} members",
                    color = TujijengeGreen,
                    fontSize = 14.sp,
                    fontFamily = nunito
                )
                Text(
                    text = "${community.address}",
                    color = TujijengeGreen,
                    fontSize = 13.sp,
                    fontFamily = nunito
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityCard() {
    CommunityCard(
        community = Community("1", "Community A", 10, "500m away", "Karen Korongo Road")
    )
}