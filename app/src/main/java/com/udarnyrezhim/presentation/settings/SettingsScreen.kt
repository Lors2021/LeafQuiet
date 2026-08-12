package com.udarnyrezhim.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.theme.*

@Composable
fun SettingsScreen() {
    var darkMode by remember { mutableStateOf(false) }
    var sounds by remember { mutableStateOf(true) }
    var reminders by remember { mutableStateOf(true) }

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
                text = "Настройки",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PlayfairDisplay,
                color = OliveDark,
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
            )

            SettingsGroup(title = "Внешний вид") {
                SettingsToggle(
                    title = "Тёмная тема",
                    subtitle = "Автоматически",
                    checked = darkMode,
                    onCheckedChange = { darkMode = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            SettingsGroup(title = "Звуки") {
                SettingsToggle(
                    title = "Фоновые звуки",
                    subtitle = "Лес, дождь, ветер",
                    checked = sounds,
                    onCheckedChange = { sounds = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            SettingsGroup(title = "Уведомления") {
                SettingsToggle(
                    title = "Ежедневное напоминание",
                    subtitle = "В 9:00 утра",
                    checked = reminders,
                    onCheckedChange = { reminders = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            SettingsGroup(title = "Данные") {
                SettingsAction(title = "Экспорт статистики", subtitle = "CSV файл")
                SettingsAction(title = "Резервное копирование", subtitle = "Google Drive")
                SettingsAction(title = "Очистить данные", subtitle = "Сбросить всё")
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Ударный режим v1.0.0",
                fontSize = 12.sp,
                color = Sage.copy(alpha = 0.6f),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun SettingsGroup(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Sage,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Paper, RoundedCornerShape(20.dp))
                .padding(vertical = 8.dp)
        ) {
            content()
        }
    }
}

@Composable
fun SettingsToggle(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 16.sp, color = OliveDark)
            Text(text = subtitle, fontSize = 12.sp, color = Sage)
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Cream,
                checkedTrackColor = OliveMedium,
                uncheckedThumbColor = Cream,
                uncheckedTrackColor = Beige
            )
        )
    }
}

@Composable
fun SettingsAction(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 16.sp, color = OliveDark)
            Text(text = subtitle, fontSize = 12.sp, color = Sage)
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Sage
        )
    }
}
