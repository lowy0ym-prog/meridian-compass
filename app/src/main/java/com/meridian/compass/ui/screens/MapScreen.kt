package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MapScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1a1a1a))
    ) {
        // Map placeholder - will integrate MapLibre here
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2a2a2a)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Map View\n(MapLibre will load here)",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Current location button
        FloatingActionButton(
            onClick = { /* Center on user */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFE74C3C)
        ) {
            Icon(
                Icons.Default.MyLocation,
                contentDescription = "Current Location",
                tint = Color.White
            )
        }

        // Back button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2a2a2a)
            )
        ) {
            Text("Back", color = Color.White)
        }
    }
}