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
fun TrailRecordScreen(navController: NavController) {
    var isRecording by remember { mutableStateOf(false) }
    var distance by remember { mutableStateOf("0.0 miles") }
    var duration by remember { mutableStateOf("00:00:00") }
    var trailName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1a1a1a))
    ) {
        // Recording info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2a2a2a))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Recording Trail",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    distance,
                    color = Color(0xFFE74C3C),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    duration,
                    color = Color(0xFFB0B0B0),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
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
                "Trail visualization",
                color = Color(0xFFB0B0B0)
            )
        }

        // Controls
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2a2a2a))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isRecording) {
                Button(
                    onClick = { isRecording = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C)
                    )
                ) {
                    Text("Stop Recording", color = Color.White, fontSize = 16.sp)
                }
            } else {
                Button(
                    onClick = { isRecording = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C)
                    )
                ) {
                    Text("Start Recording", color = Color.White, fontSize = 16.sp)
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3a3a3a)
                )
            ) {
                Text("Back", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}