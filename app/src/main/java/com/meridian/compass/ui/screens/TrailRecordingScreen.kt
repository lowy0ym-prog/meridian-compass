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
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meridian.compass.ui.theme.DarkBackground
import com.meridian.compass.ui.theme.DarkPanel
import com.meridian.compass.ui.theme.DarkSurface
import com.meridian.compass.ui.theme.LightText
import com.meridian.compass.ui.theme.MutedText
import com.meridian.compass.ui.theme.PrimaryRed
import com.meridian.compass.ui.theme.SuccessGreen

@Composable
fun TrailRecordingScreen(onBack: () -> Unit) {
    val isRecording = remember { mutableStateOf(false) }
    val distance = remember { mutableStateOf(0.0) }
    val duration = remember { mutableStateOf(0L) }
    val accuracy = remember { mutableStateOf(5f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
                    "RECORD TRAIL",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Display
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkPanel, RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        formatDuration(duration.value),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isRecording.value) PrimaryRed else LightText
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        StatBox(
                            label = "Distance",
                            value = "%.2f km".format(distance.value / 1000)
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        StatBox(
                            label = "Accuracy",
                            value = "±${accuracy.value.toInt()}m"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Recording Status
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height(12.dp)
                            .background(
                                if (isRecording.value) PrimaryRed else MutedText,
                                RoundedCornerShape(6.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        if (isRecording.value) "Recording GPS points..." else "Ready to record",
                        fontSize = 14.sp,
                        color = if (isRecording.value) SuccessGreen else MutedText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { isRecording.value = !isRecording.value },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRecording.value) PrimaryRed else SuccessGreen
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        if (isRecording.value) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isRecording.value) "Pause" else "Start",
                        modifier = Modifier.width(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (isRecording.value) "PAUSE" else "START",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { /* Stop and save */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPanel),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        Icons.Default.Stop,
                        contentDescription = "Stop",
                        modifier = Modifier.width(24.dp),
                        tint = LightText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("STOP", fontWeight = FontWeight.Bold, color = LightText)
                }
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = MutedText
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            value,
            fontSize = 18.sp,
            color = LightText,
            fontWeight = FontWeight.Bold
        )
    }
}
