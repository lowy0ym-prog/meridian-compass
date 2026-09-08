package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meridian.compass.ui.theme.DarkBackground
import com.meridian.compass.ui.theme.DarkPanel
import com.meridian.compass.ui.theme.DarkSurface
import com.meridian.compass.ui.theme.LightText
import com.meridian.compass.ui.theme.MutedText
import com.meridian.compass.ui.theme.PrimaryRed

@Composable
fun CompassScreen(onBack: () -> Unit) {
    val heading = remember { mutableStateOf(45f) }
    val bearing = remember { mutableStateOf(90f) }
    val distance = remember { mutableStateOf(250.5) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = LightText
                    )
                }
                Text(
                    "COMPASS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Compass Display
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(DarkPanel, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "${heading.value.toInt()}°",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryRed
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        getCardinalDirection(heading.value),
                        fontSize = 20.sp,
                        color = LightText
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Accuracy: ±${(Math.random() * 10).toInt()}m",
                        fontSize = 12.sp,
                        color = MutedText
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info Panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    InfoRow("Bearing", "${bearing.value.toInt()}°")
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoRow("Distance", "${distance.value} m")
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoRow("Altitude", "1,245 m")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            Button(
                onClick = { /* Lock bearing */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("LOCK BEARING", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = MutedText,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            value,
            fontSize = 14.sp,
            color = LightText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

fun getCardinalDirection(degrees: Float): String {
    return when {
        degrees < 11.25 -> "N"
        degrees < 33.75 -> "NNE"
        degrees < 56.25 -> "NE"
        degrees < 78.75 -> "ENE"
        degrees < 101.25 -> "E"
        degrees < 123.75 -> "ESE"
        degrees < 146.25 -> "SE"
        degrees < 168.75 -> "SSE"
        degrees < 191.25 -> "S"
        degrees < 213.75 -> "SSW"
        degrees < 236.25 -> "SW"
        degrees < 258.75 -> "WSW"
        degrees < 281.25 -> "W"
        degrees < 303.75 -> "WNW"
        degrees < 326.25 -> "NW"
        degrees < 348.75 -> "NNW"
        else -> "N"
    }
}
