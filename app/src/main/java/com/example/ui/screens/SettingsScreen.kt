package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        SettingsTopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RiskManagementCard(
                autoExecution = uiState.riskSettings.autoExecution,
                baseSize = uiState.riskSettings.basePositionSize,
                maxLoss = uiState.riskSettings.maxDailyLoss,
                onToggleAutoExecution = { viewModel.toggleAutoExecution() }
            )
            IntelligenceCard(activeModel = uiState.systemConfig.activeModel)
            BrokerCard(status = uiState.systemConfig.brokerStatus)
        }
    }
}

@Composable
fun SettingsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .shadow(elevation = 1.dp, spotColor = Color.Black.copy(alpha = 0.05f)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "SYSTEM CONFIGURATION",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Slate500,
                letterSpacing = 1.sp
            )
            Text(
                text = "RISK & AI CONTROL",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryBlue
            )
        }
    }
}

@Composable
fun RiskManagementCard(
    autoExecution: Boolean,
    baseSize: Double,
    maxLoss: Double,
    onToggleAutoExecution: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .border(1.dp, Slate100, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(PrimaryLight, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🛡️", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Risk Engine", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Slate800)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Auto Execution Gate", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Slate800)
                Text("Allow AI to trade without manual approval", fontSize = 11.sp, color = Slate400)
            }
            Switch(
                checked = autoExecution,
                onCheckedChange = { onToggleAutoExecution() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = SuccessGreen,
                    uncheckedThumbColor = Slate400,
                    uncheckedTrackColor = Slate200
                )
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Slate100)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Base Position Size", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Slate500)
            Text(String.format("$%.2f", baseSize), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Slate800)
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Max Daily Loss", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Slate500)
            Text(String.format("$%.2f", maxLoss), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFB7185))
        }
    }
}

@Composable
fun IntelligenceCard(activeModel: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryContainer, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🧠", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Intelligence Module", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Active Model", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Slate500)
                Text(activeModel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Slate800)
            }
            Box(
                modifier = Modifier
                    .background(PrimaryLight, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text("CHANGE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
            }
        }
    }
}

@Composable
fun BrokerCard(status: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceDark, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("⚡", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("IQ Option Adapter", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("WebSocket Status", fontSize = 14.sp, color = Slate400)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(SuccessGreen, CircleShape)
                        .shadow(elevation = 8.dp, spotColor = SuccessGreen, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(status, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
            }
        }
    }
}
