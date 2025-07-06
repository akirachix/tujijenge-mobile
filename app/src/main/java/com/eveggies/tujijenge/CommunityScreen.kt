package com.eveggies.tujijenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunityScreen(
    communities: List<Community>,
    onBackClick: () -> Unit
) {
    val TujijengeGreen = Color(0xFF084236)
    val TujijengeWhite = Color(0xFFFFFFFF)
    val TujijengeLightGreen = Color(0xFFC0C882)
    val nunito = FontFamily(Font(R.font.nunito))
    var searchQuery by remember { mutableStateOf("") }
    val filteredCommunities = communities.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TujijengeWhite)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = TujijengeGreen
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Choose Community",
            fontSize = 32.sp,
            color = TujijengeGreen,
            fontWeight = FontWeight.Bold,
            fontFamily = nunito
        )



        Text(
            text = "*You can only join one community at a time.",
            color = TujijengeGreen,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .padding(horizontal = 23.dp),
            fontFamily = nunito
        )



        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search Community", color = TujijengeGreen, fontFamily = nunito, fontSize = 16.sp)  },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TujijengeGreen,
                    modifier = Modifier.padding(start = 30.dp)

                )
            },




            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TujijengeLightGreen,
                unfocusedBorderColor = TujijengeLightGreen,
                focusedContainerColor = TujijengeWhite,
                unfocusedContainerColor = TujijengeWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)

        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(filteredCommunities) { community ->
                CommunityCard(
                    community = community,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCommunityScreen() {
    val tryCommunities = listOf(
        Community("1", "Community A", 10, "500m away", "Karen Korongo Road, Nairobi, Kenya"),
        Community("2", "Community B", 10, "500m away", "Nairobi Kenya , Nairobi, Kenya"),
        Community("3", "Community C", 10, "500m away", "Karen Korongo Road , Nairobi, Kenya")
    )
    CommunityScreen(communities = tryCommunities, onBackClick = {})
}