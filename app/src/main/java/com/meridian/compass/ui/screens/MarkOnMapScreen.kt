package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun MarkOnMapScreen(navController: NavController) {
    var selectedLat by remember { mutableStateOf(40.7128) }
    var selectedLon by remember { mutableStateOf(-74.0060) }
    var waypointName by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("0 miles") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1a1a1a))
    ) {
        // Coordinates display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2a2a2a))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Selected Location",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Lat: $selectedLat",
                    color = Color(0xFFB0B0B0),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "Lon: $selectedLon",
                    color = Color(0xFFB0B0B0),
                    fontSize = 12.sp
                )
                Text(
                    "Distance: $distance",
                    color = Color(0xFFE74C3C),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Map area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF2a2a2a)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Map - Tap to select location",
                color = Color(0xFFB0B0B0)
            )
        }

        // Name input and buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2a2a2a))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = waypointName,
                onValueChange = { waypointName = it },
                label = { Text("Waypoint Name", color = Color(0xFFB0B0B0)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1a1a1a),
                    unfocusedContainerColor = Color(0xFF1a1a1a),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3a3a3a)
                    )
                ) {
                    Text("Cancel", color = Color.White)
                }
                Button(
                    onClick = { /* Save waypoint */ navController.popBackStack() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C)
                    )
                ) {
                    Text("Use this location", color = Color.White)
                }
            }
        }
    }
}