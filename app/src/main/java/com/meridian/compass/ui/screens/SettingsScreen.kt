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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
fun SettingsScreen(onBack: () -> Unit) {
    val magneticNorth = remember { mutableStateOf(true) }
    val imperialUnits = remember { mutableStateOf(true) }
    val preciseDistance = remember { mutableStateOf(false) }
    val compassHaptics = remember { mutableStateOf(true) }

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
                    "SETTINGS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                // Compass Settings
                SettingsGroup(title = "Compass") {
                    SettingRow(
                        label = "Magnetic North",
                        checked = magneticNorth.value,
                        onCheckedChange = { magneticNorth.value = it }
                    )
                    SettingRow(
                        label = "Compass Haptics",
                        checked = compassHaptics.value,
                        onCheckedChange = { compassHaptics.value = it }
                    )
                }

                // Units Settings
                SettingsGroup(title = "Units") {
                    SettingRow(
                        label = "Imperial (mi/ft)",
                        checked = imperialUnits.value,
                        onCheckedChange = { imperialUnits.value = it }
                    )
                    SettingRow(
                        label = "Precise Distance",
                        checked = preciseDistance.value,
                        onCheckedChange = { preciseDistance.value = it }
                    )
                }

                // Data Management
                SettingsGroup(title = "Data Management") {
                    Button(
                        onClick = { /* Export trails */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Export Trails (GPX)", fontSize = 12.sp, color = LightText)
                    }
                    Button(
                        onClick = { /* Delete waypoints */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Delete All Waypoints", fontSize = 12.sp, color = LightText)
                    }
                    Button(
                        onClick = { /* Delete trails */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Delete All Trails", fontSize = 12.sp, color = LightText)
                    }
                }

                // About
                SettingsGroup(title = "About") {
                    Text(
                        "Meridian Compass v1.0.0",
                        fontSize = 14.sp,
                        color = LightText,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Offline-first GPS navigation and waypoint app",
                        fontSize = 12.sp,
                        color = MutedText
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsGroup(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = LightText,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkPanel, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                content()
            }
        }
    }
}

@Composable
fun SettingRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            fontSize = 14.sp,
            color = LightText,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = PrimaryRed,
                checkedTrackColor = SuccessGreen
            )
        )
    }
}
