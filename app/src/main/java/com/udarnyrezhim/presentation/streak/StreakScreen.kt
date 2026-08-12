package com.udarnyrezhim.presentation.streak

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.components.GlassmorphismCard
import com.udarnyrezhim.presentation.theme.*

@Composable
fun StreakScreen(
    currentStreak: Int = 12,
    longestStreak: Int = 45
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        FloatingLeavesBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FlameAnimation()

            Spacer(Modifier.height(24.dp))

            Text(
                text = "$currentStreak",
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark
            )

            Text(
                text = "дней подряд",
                fontSize = 18.sp,
                color = Sage
            )

            Spacer(Modifier.height(32.dp))

            GlassmorphismCard {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Лучший результат",
                        fontSize = 14.sp,
                        color = Sage
                    )
                    Text(
                        text = "$longestStreak дней",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = PlayfairDisplay,
                        color = OliveDark
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            GlassmorphismCard {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Следующая награда",
                        fontSize = 14.sp,
                        color = Sage
                    )
                    Text(
                        text = "${30 - currentStreak} дней до золотого листа",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = OliveDark
                    )
                }
            }
        }
    }
}

@Composable
fun FlameAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "flame")

    val scale1 by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            tween(800, easing = EaseInOutSine),
            RepeatMode.Reverse
        ),
        label = "scale1"
    )

    val scale2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            tween(1000, easing = EaseInOutSine),
            RepeatMode.Reverse
        ),
        label = "scale2"
    )

    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🔥",
            fontSize = 48.sp,
            modifier = Modifier.scale(scale1)
        )
        Text(
            text = "🍃",
            fontSize = 32.sp,
            modifier = Modifier
                .offset(y = (-20).dp)
                .scale(scale2)
        )
    }
}
