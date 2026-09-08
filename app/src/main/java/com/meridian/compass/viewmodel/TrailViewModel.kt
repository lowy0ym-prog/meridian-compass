package com.meridian.compass.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridian.compass.data.models.Trail
import com.meridian.compass.data.repositories.TrailRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrailViewModel(private val repository: TrailRepository) : ViewModel() {
    
    val trails: StateFlow<List<Trail>> = repository.allTrails
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTrail(trail: Trail) = viewModelScope.launch {
        repository.insert(trail)
    }

    fun updateTrail(trail: Trail) = viewModelScope.launch {
        repository.update(trail)
    }

    fun deleteTrail(trail: Trail) = viewModelScope.launch {
        repository.delete(trail)
    }
}
