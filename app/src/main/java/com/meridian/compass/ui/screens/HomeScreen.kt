package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1a1a1a))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Meridian Compass",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                "Your Private Navigation App",
                fontSize = 14.sp,
                color = Color(0xFFB0B0B0),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Action Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionButton(
                icon = Icons.Default.Search,
                title = "Search",
                description = "Find a location",
                onClick = { navController.navigate("search") }
            )
            ActionButton(
                icon = Icons.Default.Map,
                title = "Start or Join Rally",
                description = "Coordinate with others",
                onClick = { navController.navigate("rally") }
            )
            ActionButton(
                icon = Icons.Default.Timeline,
                title = "Record Trail",
                description = "Track your path",
                onClick = { navController.navigate("record_trail") }
            )
            ActionButton(
                icon = Icons.Default.LocationOn,
                title = "Mark on Map",
                description = "Select a point",
                onClick = { navController.navigate("mark_on_map") }
            )
            ActionButton(
                icon = Icons.Default.LocationOn,
                title = "Mark My Location",
                description = "Save current position",
                onClick = { /* Will implement */ }
            )
        }

        // Bottom button
        Button(
            onClick = { navController.navigate("waypoints") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE74C3C)
            )
        ) {
            Text("View Waypoints", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun ActionButton(
    icon: androidx.compose.material.icons.Icons.Filled,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2a2a2a), shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFFE74C3C),
            modifier = Modifier.size(28.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                description,
                color = Color(0xFFB0B0B0),
                fontSize = 12.sp
            )
        }
    }
}