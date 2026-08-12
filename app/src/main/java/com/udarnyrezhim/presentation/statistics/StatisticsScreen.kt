package com.udarnyrezhim.presentation.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.components.GlassmorphismCard
import com.udarnyrezhim.presentation.theme.*

@Composable
fun StatisticsScreen(
    daysCompleted: Int = 42,
    totalFocusHours: Int = 28,
    currentStreak: Int = 12,
    longestStreak: Int = 45,
    tasksCompleted: Int = 156
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
                .padding(24.dp)
        ) {
            Text(
                text = "Статистика",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Твой прогресс в цифрах",
                fontSize = 14.sp,
                color = Sage,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    value = "$daysCompleted",
                    label = "Дней выполнено",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = "$totalFocusHours",
                    label = "Часов в фокусе",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    value = "$currentStreak",
                    label = "Текущий стрик",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = "$longestStreak",
                    label = "Лучший стрик",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(12.dp))

            StatCard(
                value = "$tasksCompleted",
                label = "Задач выполнено",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Недельный прогресс",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = OliveDark,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            WeeklyChart()

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Ежемесячный прогресс",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = OliveDark,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            MonthlyChart()
        }
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    GlassmorphismCard(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Sage,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun WeeklyChart() {
    val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    val values = listOf(0.6f, 0.8f, 1f, 0.4f, 0.9f, 0.3f, 0.7f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Paper, RoundedCornerShape(20.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        days.forEachIndexed { index, day ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .fillMaxHeight(values[index])
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (values[index] >= 1f) OliveMedium else Sage.copy(alpha = 0.5f)
                        )
                )
                Spacer(Modifier.height(6.dp))
                Text(text = day, fontSize = 11.sp, color = Sage)
            }
        }
    }
}

@Composable
fun MonthlyChart() {
    val weeks = 4
    val daysInWeek = 7

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Paper, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        repeat(weeks) { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(daysInWeek) { day ->
                    val isCompleted = (week * 7 + day) % 3 != 0
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isCompleted) OliveMedium.copy(alpha = 0.8f)
                                else Beige.copy(alpha = 0.5f)
                            )
                    )
                }
            }
            if (week < weeks - 1) Spacer(Modifier.height(4.dp))
        }
    }
}
