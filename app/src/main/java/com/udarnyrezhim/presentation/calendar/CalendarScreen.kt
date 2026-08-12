package com.udarnyrezhim.presentation.calendar

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.theme.*

@Composable
fun CalendarScreen(
    completedDays: Set<Int> = setOf(1, 2, 5, 6, 7, 8, 12, 13, 14, 15, 19, 20, 21),
    currentDay: Int = 24
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
                text = "Календарь",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Август 2026",
                fontSize = 18.sp,
                color = Sage,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс").forEach { day ->
                    Text(
                        text = day,
                        fontSize = 12.sp,
                        color = Sage,
                        modifier = Modifier.width(36.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            val daysInMonth = 31
            val startOffset = 4

            var dayCounter = 1 - startOffset

            repeat(6) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(7) {
                        if (dayCounter in 1..daysInMonth) {
                            val isCompleted = completedDays.contains(dayCounter)
                            val isCurrent = dayCounter == currentDay

                            DayCell(
                                day = dayCounter,
                                isCompleted = isCompleted,
                                isCurrent = isCurrent
                            )
                        } else {
                            Box(modifier = Modifier.size(40.dp))
                        }
                        dayCounter++
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(OliveMedium)
                )
                Text(" Выполнено", fontSize = 12.sp, color = Sage, modifier = Modifier.padding(end = 16.dp))

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Beige)
                )
                Text(" Пропущено", fontSize = 12.sp, color = Sage, modifier = Modifier.padding(end = 16.dp))

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(OliveDark)
                )
                Text(" Сегодня", fontSize = 12.sp, color = Sage)
            }
        }
    }
}

@Composable
fun DayCell(day: Int, isCompleted: Boolean, isCurrent: Boolean) {
    val bgColor = when {
        isCurrent -> OliveDark
        isCompleted -> OliveMedium.copy(alpha = 0.8f)
        else -> Beige.copy(alpha = 0.5f)
    }

    val textColor = when {
        isCurrent -> Cream
        isCompleted -> Cream
        else -> OliveDark.copy(alpha = 0.5f)
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$day",
            fontSize = 14.sp,
            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}
