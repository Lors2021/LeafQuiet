package com.udarnyrezhim.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.*
import com.udarnyrezhim.presentation.theme.*

@Composable
fun HomeScreen(
    currentStreak: Int = 12,
    bestStreak: Int = 45,
    focusTimeMinutes: Int = 45,
    onStartFocus: () -> Unit = {}
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Доброе утро", fontSize = 14.sp, color = Sage)
                    Text(
                        "Понедельник, 2 августа",
                        fontSize = 12.sp,
                        color = Sage.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Text(
                "Ударный режим",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark
            )
            Text(
                "Сегодня нужно выполнить всего одну задачу.",
                fontSize = 16.sp,
                color = Sage,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(40.dp))

            ProgressRing(
                progress = currentStreak.toFloat() / bestStreak.coerceAtLeast(1),
                size = 220.dp,
                centerContent = { StreakCenterContent(currentStreak, bestStreak) }
            )

            Spacer(Modifier.height(24.dp))

            GlassmorphismCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Сегодня в фокусе", fontSize = 12.sp, color = Sage)
                        Text(
                            "$focusTimeMinutes мин",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = PlayfairDisplay,
                            color = OliveDark
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(width = 80.dp, height = 4.dp)
                            .background(Sage.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(focusTimeMinutes / 60f)
                                .height(4.dp)
                                .background(OliveMedium, RoundedCornerShape(2.dp))
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            PrimaryButton(
                text = "Начать ударный режим",
                onClick = onStartFocus,
                icon = Icons.Default.PlayArrow
            )

            Spacer(Modifier.height(32.dp))
        }
    }
}
