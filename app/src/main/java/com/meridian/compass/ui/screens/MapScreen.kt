package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun MapScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                    "MAP",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Map Placeholder
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(DarkPanel)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Map View",
                        fontSize = 18.sp,
                        color = LightText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "MapLibre integration active",
                        fontSize = 12.sp,
                        color = MutedText
                    )
                }
            }

            // GPS Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface)
                    .padding(16.dp)
            ) {
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Current Location",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "37.7749° N, 122.4194° W",
                            fontSize = 14.sp,
                            color = LightText,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Accuracy: ±5m",
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }
                    Button(
                        onClick = { /* Center on location */ },
                        modifier = Modifier.height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            Icons.Default.MyLocation,
                            contentDescription = "Center",
                            modifier = Modifier.width(20.dp)
                        )
                    }
                }
            }
        }
    }
}
