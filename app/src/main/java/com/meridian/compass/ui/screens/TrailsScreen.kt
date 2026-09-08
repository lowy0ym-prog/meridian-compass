package com.meridian.compass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
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
import com.meridian.compass.data.models.Trail
import com.meridian.compass.ui.theme.DarkBackground
import com.meridian.compass.ui.theme.DarkPanel
import com.meridian.compass.ui.theme.LightText
import com.meridian.compass.ui.theme.MutedText
import com.meridian.compass.ui.theme.PrimaryRed
import com.meridian.compass.ui.theme.SuccessGreen
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun TrailsScreen(onBack: () -> Unit, trails: List<Trail> = emptyList()) {
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
                    "TRAILS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Trails List
            if (trails.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "No Trails Yet",
                            fontSize = 18.sp,
                            color = LightText,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Record a trail to get started",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
                ) {
                    items(trails) { trail ->
                        TrailCard(trail)
                    }
                }
            }

            // Action Button
            Button(
                onClick = { /* Record new trail */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Record",
                    modifier = Modifier.width(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("RECORD NEW TRAIL", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TrailCard(trail: Trail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkPanel, RoundedCornerShape(8.dp))
            .padding(12.dp)
            .clickable { /* View trail */ }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        trail.name,
                        fontSize = 14.sp,
                        color = LightText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val date = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(trail.startedAt),
                        ZoneId.systemDefault()
                    )
                    Text(
                        date.toString(),
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
                IconButton(
                    onClick = { /* Delete trail */ },
                    modifier = Modifier.width(40.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = PrimaryRed,
                        modifier = Modifier.width(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ) {
                TrailStat(label = "Distance", value = "%.1f km".format(trail.totalDistance / 1000))
                TrailStat(label = "Duration", value = formatDuration(trail.totalDuration))
                TrailStat(
                    label = "Points",
                    value = "${(Math.random() * 100).toInt()}",
                    color = SuccessGreen
                )
            }
        }
    }
}

@Composable
fun TrailStat(label: String, value: String, color: androidx.compose.ui.graphics.Color = LightText) {
    Column {
        Text(
            label,
            fontSize = 10.sp,
            color = MutedText
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            value,
            fontSize = 12.sp,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}

fun formatDuration(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    return when {
        hours > 0 -> "%dh %dm".format(hours, minutes)
        minutes > 0 -> "%dm %ds".format(minutes, secs)
        else -> "%ds".format(secs)
    }
}
