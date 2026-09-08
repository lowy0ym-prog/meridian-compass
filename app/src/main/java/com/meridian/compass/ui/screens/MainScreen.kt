package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meridian.compass.ui.theme.DarkBackground
import com.meridian.compass.ui.theme.DarkPanel
import com.meridian.compass.ui.theme.LightText
import com.meridian.compass.ui.theme.PrimaryRed

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "MERIDIAN COMPASS",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    "Offline GPS Navigation",
                    fontSize = 14.sp,
                    color = com.meridian.compass.ui.theme.MutedText,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Main Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainActionButton(
                    label = "SEARCH",
                    icon = Icons.Default.Map,
                    onClick = { /* Navigate to search */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                MainActionButton(
                    label = "START OR JOIN A RALLY",
                    icon = Icons.Default.LocationOn,
                    onClick = { /* Navigate to rally */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                MainActionButton(
                    label = "RECORD TRAIL",
                    icon = Icons.Default.Map,
                    onClick = { /* Navigate to trail recording */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                MainActionButton(
                    label = "MARK ON MAP",
                    icon = Icons.Default.Map,
                    onClick = { /* Navigate to mark on map */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                MainActionButton(
                    label = "MARK MY LOCATION",
                    icon = Icons.Default.LocationOn,
                    onClick = { /* Mark current location */ }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bottom Controls
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Open Compass */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPanel),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("COMPASS", color = LightText, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { /* Open Map */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPanel),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("MAP", color = LightText, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { /* Open Settings */ },
                    modifier = Modifier
                        .size(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPanel),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = LightText,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActionButton(
    label: String,
    icon: androidx.compose.material.icons.Icons.Filled,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 12.dp)
            )
            Text(
                label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
