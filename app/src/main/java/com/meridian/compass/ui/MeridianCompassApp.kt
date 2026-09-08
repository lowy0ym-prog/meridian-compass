package com.meridian.compass.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meridian.compass.ui.screens.HomeScreen
import com.meridian.compass.ui.screens.MapScreen
import com.meridian.compass.ui.screens.MarkOnMapScreen
import com.meridian.compass.ui.screens.TrailRecordScreen
import com.meridian.compass.ui.screens.WaypointsScreen
import com.meridian.compass.ui.screens.SearchScreen
import com.meridian.compass.ui.screens.RallyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeridianCompassApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("map") {
            MapScreen(navController)
        }
        composable("mark_on_map") {
            MarkOnMapScreen(navController)
        }
        composable("record_trail") {
            TrailRecordScreen(navController)
        }
        composable("waypoints") {
            WaypointsScreen(navController)
        }
        composable("search") {
            SearchScreen(navController)
        }
        composable("rally") {
            RallyScreen(navController)
        }
    }
}