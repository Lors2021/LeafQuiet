package com.udarnyrezhim.presentation.focus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.components.PrimaryButton
import com.udarnyrezhim.presentation.theme.*

@Composable
fun FocusSetupScreen(
    onStartFocus: (Int, String) -> Unit = { _, _ -> }
) {
    val durations = listOf(15, 25, 30, 45, 60)
    var selectedDuration by remember { mutableIntStateOf(25) }
    val apps = listOf("Коран", "Заметки", "PDF reader", "Браузер", "Другое")
    var selectedApp by remember { mutableStateOf(apps.first()) }

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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Настрой фокус",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Выбери длительность и одно разрешённое приложение",
                fontSize = 14.sp,
                color = Sage,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
            )

            Text(
                text = "Длительность",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OliveDark,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                durations.forEach { duration ->
                    val isSelected = duration == selectedDuration
                    Box(
                        modifier = Modifier
                            .size(width = 56.dp, height = 48.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) OliveDark else Color.Transparent)
                            .border(
                                2.dp,
                                if (isSelected) OliveDark else Sage.copy(alpha = 0.4f),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedDuration = duration },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$duration",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) Cream else OliveDark
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Разрешённое приложение",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OliveDark,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(12.dp))

            apps.forEach { app ->
                val isSelected = app == selectedApp
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (isSelected) Sage.copy(alpha = 0.25f) else Color.Transparent
                        )
                        .border(
                            1.5.dp,
                            if (isSelected) OliveMedium else Sage.copy(alpha = 0.3f),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable { selectedApp = app }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (isSelected) OliveMedium else Color.Transparent
                            )
                            .border(
                                2.dp,
                                if (isSelected) OliveMedium else Sage,
                                RoundedCornerShape(50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Cream,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }

                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = app,
                        fontSize = 16.sp,
                        color = OliveDark,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            PrimaryButton(
                text = "Начать",
                onClick = { onStartFocus(selectedDuration, selectedApp) },
                icon = Icons.Default.ArrowForward
            )
        }
    }
}
