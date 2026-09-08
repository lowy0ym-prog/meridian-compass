package com.meridian.compass.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridian.compass.data.models.Waypoint
import com.meridian.compass.data.repositories.WaypointRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WaypointViewModel(private val repository: WaypointRepository) : ViewModel() {
    
    val waypoints: StateFlow<List<Waypoint>> = repository.allWaypoints
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addWaypoint(waypoint: Waypoint) = viewModelScope.launch {
        repository.insert(waypoint)
    }

    fun updateWaypoint(waypoint: Waypoint) = viewModelScope.launch {
        repository.update(waypoint)
    }

    fun deleteWaypoint(waypoint: Waypoint) = viewModelScope.launch {
        repository.delete(waypoint)
    }
}
