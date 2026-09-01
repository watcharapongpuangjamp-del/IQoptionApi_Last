package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar() }
                ) { innerPadding ->
                    DashboardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        TopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ActiveStrategyCard()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SignalFlowCard(modifier = Modifier.weight(1f))
                WinRateCard(modifier = Modifier.weight(1f))
            }
            PendingExecutionCard(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TopBar() {
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
                text = "PYTHON CORE SYSTEM",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Slate500,
                letterSpacing = 1.sp
            )
            Text(
                text = "IQ AI TRADER",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryBlue
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(PrimaryLight, RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(SuccessGreen, CircleShape)
                    .shadow(elevation = 8.dp, spotColor = SuccessGreen, shape = CircleShape)
            )
            Text(
                text = "LIVE",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }
    }
}

@Composable
fun ActiveStrategyCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, spotColor = Color.Black.copy(alpha = 0.04f), shape = RoundedCornerShape(28.dp))
            .background(Color.White, RoundedCornerShape(28.dp))
            .border(1.dp, Slate100, RoundedCornerShape(28.dp))
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "ACTIVE STRATEGY",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate500,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "AI Momentum Scalper",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate800
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "v0.1.3-myiq",
                        fontSize = 10.sp,
                        color = Slate400,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "BTC/USD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4F46E5) // Indigo-600
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val heights = listOf(0.4f, 0.55f, 0.7f, 0.65f, 0.8f, 0.95f, 0.85f, 0.6f, 0.45f, 0.3f, 0.2f, 0.4f)
                val colors = listOf(Slate100, Slate100, PrimaryBlue, PrimaryBlue, Slate100, PrimaryBlue, PrimaryBlue, Color(0xFFFB7185), Color(0xFFFB7185), Slate100, Slate100, PrimaryBlue)
                
                heights.forEachIndexed { index, heightFactor ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(heightFactor)
                            .padding(horizontal = 2.dp)
                            .background(colors[index], RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                    )
                }
            }
        }
    }
}

@Composable
fun SignalFlowCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(PrimaryContainer, RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "SIGNAL FLOW",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001D36).copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        SignalFlowStep(step = "1", title = "AI Analysis", isActive = false)
        Spacer(modifier = Modifier.height(6.dp))
        SignalFlowStep(step = "2", title = "Risk Filter", isActive = false)
        Spacer(modifier = Modifier.height(6.dp))
        SignalFlowStep(step = "3", title = "Execution", isActive = true)
    }
}

@Composable
fun SignalFlowStep(step: String, title: String, isActive: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(if (isActive) Color.White else Color(0xFF2563EB), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = step,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = if (isActive) Color(0xFF2563EB) else Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = if (isActive) Color(0xFF1D4ED8) else Color(0xFF001D36)
        )
    }
}

@Composable
fun WinRateCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White, RoundedCornerShape(24.dp))
            .border(1.dp, Slate100, RoundedCornerShape(24.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "WIN RATE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Slate400
        )
        
        Column {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "72.4",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = Slate800
                )
                Text(
                    text = "%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate500,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Slate100, CircleShape)
                    .padding(top = 8.dp)
            ) {
                 Box(
                    modifier = Modifier
                        .fillMaxWidth(0.724f)
                        .fillMaxHeight()
                        .background(SuccessGreen, CircleShape)
                 )
            }
        }
    }
}

@Composable
fun PendingExecutionCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceDark, RoundedCornerShape(32.dp))
            .padding(24.dp)
            .clip(RoundedCornerShape(32.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pending Execution",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF93C5FD) // blue-300
                )
                Text(
                    text = "WAITING",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF93C5FD),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .background(Color(0xFF1E3A8A).copy(alpha = 0.4f), CircleShape) // blue-900/40
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
            
            Column(verticalArrangement = Arrangement.Center) {
                ExecutionRow(label = "Expected Profit", value = "+$42.80", valueColor = SuccessGreen)
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(16.dp))
                ExecutionRow(label = "AI Confidence", value = "88%", valueColor = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Execution Gate", fontSize = 14.sp, color = Slate400)
                    Text(text = "SECURED", fontSize = 14.sp, color = Color(0xFF60A5FA), fontWeight = FontWeight.Normal) // blue-400
                }
            }
            
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "MANUAL OVERRIDE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
fun ExecutionRow(label: String, value: String, valueColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 14.sp, color = Slate400)
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = valueColor)
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Slate100)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(title = "Trade", isSelected = true)
        BottomNavItem(title = "History", isSelected = false)
        BottomNavItem(title = "Signals", isSelected = false)
        BottomNavItem(title = "Settings", isSelected = false)
    }
}

@Composable
fun BottomNavItem(title: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(PrimaryBlue, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.White, RoundedCornerShape(2.dp))
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Slate200, RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) PrimaryBlue else Slate500,
            modifier = Modifier.alpha(if (isSelected) 1f else 0.4f)
        )
    }
}
