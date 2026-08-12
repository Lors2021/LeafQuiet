package com.udarnyrezhim.presentation.focus

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.components.PrimaryButton
import com.udarnyrezhim.presentation.theme.*

val quotes = listOf(
    "Не жди мотивацию. Начни.",
    "Один день кажется маленьким. Сто дней меняют человека.",
    "Сегодняшние 20 минут создают завтрашний результат.",
    "Дисциплина — это выбор, который становится привычкой.",
    "Маленькие шаги каждый день — это большой путь за год.",
    "Фокус на одном — ключ к глубине.",
    "Сегодняшняя задача — единственное, что имеет значение.",
    "Каждый завершённый день — победа.",
    "Не идеальность, а постоянство создаёт результат.",
    "Один шаг сегодня лучше, чем идеальный план на завтра."
)

@Composable
fun FocusCompletionScreen(
    quote: String = quotes.random(),
    onContinue: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "celebration")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = EaseInOutSine),
            RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(modifier = Modifier.fillMaxSize().background(Cream)) {
        FloatingLeavesBackground(leafCount = 12)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🍃",
                fontSize = 64.sp,
                modifier = Modifier.scale(scale)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Ударный режим завершён",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Sage.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = "«$quote»",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = OliveDark,
                    textAlign = TextAlign.Center,
                    fontFamily = PlayfairDisplay,
                    lineHeight = 28.sp
                )
            }

            Spacer(Modifier.height(40.dp))

            PrimaryButton(
                text = "Вернуться на главный",
                onClick = onContinue
            )
        }
    }
}
