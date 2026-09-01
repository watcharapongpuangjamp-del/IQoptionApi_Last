package com.example.model

data class StrategyData(
    val name: String = "AI Momentum Scalper",
    val version: String = "v0.1.3-myiq",
    val pair: String = "BTC/USD",
    val winRate: Double = 72.4,
    val isLive: Boolean = true
)

data class PendingExecution(
    val expectedProfit: Double = 42.80,
    val aiConfidence: Int = 88,
    val status: String = "WAITING",
    val gateStatus: String = "SECURED"
)

data class RiskSettings(
    val autoExecution: Boolean = false,
    val basePositionSize: Double = 10.0,
    val maxDailyLoss: Double = 100.0,
    val maxDrawdownPct: Double = 5.0
)

data class SystemConfig(
    val activeModel: String = "Champion (v1.2)",
    val brokerStatus: String = "CONNECTED"
)

data class DashboardState(
    val strategyData: StrategyData = StrategyData(),
    val pendingExecution: PendingExecution? = PendingExecution(),
    val isGateOpen: Boolean = true,
    val riskSettings: RiskSettings = RiskSettings(),
    val systemConfig: SystemConfig = SystemConfig()
)
