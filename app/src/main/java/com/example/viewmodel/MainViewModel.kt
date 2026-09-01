package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.DashboardState
import com.example.model.PendingExecution
import com.example.model.StrategyData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardState())
    val uiState: StateFlow<DashboardState> = _uiState.asStateFlow()

    init {
        // Simulate connecting to Python Core via WebSocket / Firebase
        simulateRealTimeUpdates()
    }

    private fun simulateRealTimeUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(3000)
                // Simulate data changing from backend
                _uiState.update { currentState ->
                    val newConfidence = (80..99).random()
                    val newProfit = (30..60).random().toDouble() + (0..99).random() / 100.0
                    val currentPending = currentState.pendingExecution
                    
                    currentState.copy(
                        pendingExecution = currentPending?.copy(
                            aiConfidence = newConfidence,
                            expectedProfit = newProfit
                        )
                    )
                }
            }
        }
    }

    fun toggleGate() {
        _uiState.update { 
            it.copy(isGateOpen = !it.isGateOpen)
        }
    }

    fun toggleAutoExecution() {
        _uiState.update {
            val currentRisk = it.riskSettings
            it.copy(riskSettings = currentRisk.copy(autoExecution = !currentRisk.autoExecution))
        }
    }

    fun manualOverride() {
        _uiState.update { 
            it.copy(pendingExecution = null) // clear pending
        }
        
        viewModelScope.launch {
            delay(5000) // bring it back after some time
            _uiState.update { 
                it.copy(pendingExecution = PendingExecution()) 
            }
        }
    }
}
