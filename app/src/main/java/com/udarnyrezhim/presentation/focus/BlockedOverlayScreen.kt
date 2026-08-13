package com.udarnyrezhim.presentation.focus

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.theme.DarkBackground
import com.udarnyrezhim.presentation.theme.OliveMedium
import com.udarnyrezhim.presentation.theme.PlayfairDisplay

@Composable
fun BlockedOverlayScreen(
    remainingMinutes: Int = 18,
    onReturn: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground.copy(alpha = 0.95f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(OliveMedium.copy(alpha = 0.3f), RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            ) {
                Text("🍃", fontSize = 40.sp)
            }

            Text(
                text = "Сначала закончи ударный режим.",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PlayfairDisplay,
                color = Color(0xFFF5F0E8),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp, horizontal = 32.dp)
            )

            Text(
                text = "Осталось $remainingMinutes минут",
                fontSize = 16.sp,
                color = Color(0xFFF5F0E8).copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 12.dp)
            )

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val scale by animateFloatAsState(
                targetValue = if (isPressed) 0.97f else 1f,
                label = "scale"
            )

            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .scale(scale)
                    .background(OliveMedium, RoundedCornerShape(20.dp))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onReturn
                    )
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Вернуться",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF5F0E8)
                )
            }
        }
    }
}
