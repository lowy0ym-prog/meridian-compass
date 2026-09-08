package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1a1a1a))
    ) {
        // Search bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2a2a2a))
                .padding(16.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search location...", color = Color(0xFFB0B0B0)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFE74C3C)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1a1a1a),
                    unfocusedContainerColor = Color(0xFF1a1a1a),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        }

        // Results area
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1a1a1a)),
            contentAlignment = Alignment.Center
        ) {
            if (searchQuery.isEmpty()) {
                Text(
                    "Enter a location to search",
                    color = Color(0xFFB0B0B0),
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Searching for: $searchQuery",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Results will appear here",
                        color = Color(0xFFB0B0B0),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // Back button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3a3a3a)
            )
        ) {
            Text("Back", color = Color.White, fontSize = 16.sp)
        }
    }
}