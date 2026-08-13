package com.udarnyrezhim.presentation.focus

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.ProgressRing
import com.udarnyrezhim.presentation.theme.DarkBackground
import com.udarnyrezhim.presentation.theme.PlayfairDisplay
import kotlinx.coroutines.delay

@Composable
fun FocusActiveScreen(
    durationMinutes: Int = 25,
    taskName: String = "Повторить суры",
    allowedApp: String = "Коран",
    onComplete: () -> Unit = {}
) {
    var remainingSeconds by remember { mutableIntStateOf(durationMinutes * 60) }
    var isPaused by remember { mutableStateOf(false) }

    LaunchedEffect(isPaused) {
        while (remainingSeconds > 0 && !isPaused) {
            delay(1000L)
            remainingSeconds--
        }
        if (remainingSeconds <= 0) onComplete()
    }

    val progress = 1f - (remainingSeconds.toFloat() / (durationMinutes * 60))
    val timeText = String.format("%02d:%02d", remainingSeconds / 60, remainingSeconds % 60)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ProgressRing(
                progress = progress,
                size = 280.dp,
                strokeWidth = 14.dp,
                centerContent = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = timeText,
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = PlayfairDisplay,
                            color = Color(0xFFF5F0E8)
                        )
                        Text(
                            text = "осталось",
                            fontSize = 14.sp,
                            color = Color(0xFFF5F0E8).copy(alpha = 0.6f)
                        )
                    }
                }
            )

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .background(
                        Color.White.copy(alpha = 0.08f),
                        RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.1f),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = taskName,
                        fontSize = 16.sp,
                        color = Color(0xFFF5F0E8)
                    )
                    Text(
                        text = allowedApp,
                        fontSize = 12.sp,
                        color = Color(0xFFF5F0E8).copy(alpha = 0.5f)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val scale by animateFloatAsState(
                    targetValue = if (isPressed) 0.95f else 1f,
                    label = "scale"
                )

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .scale(scale)
                        .background(Color.White.copy(alpha = 0.1f), CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { isPaused = !isPaused },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Pause,
                        contentDescription = null,
                        tint = Color(0xFFF5F0E8),
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = "Завершить",
                    fontSize = 14.sp,
                    color = Color(0xFFF5F0E8).copy(alpha = 0.5f)
                )
            }
        }
    }
}
