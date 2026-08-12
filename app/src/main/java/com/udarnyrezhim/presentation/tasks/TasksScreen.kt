package com.udarnyrezhim.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.components.FloatingLeavesBackground
import com.udarnyrezhim.presentation.components.GlassmorphismCard
import com.udarnyrezhim.presentation.components.LeafCheckbox
import com.udarnyrezhim.presentation.theme.*

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)

@Composable
fun TasksScreen(
    tasks: List<Task> = listOf(
        Task(1, "Повторить старые суры"),
        Task(2, "Выучить 10 новых аятов"),
        Task(3, "Читать вслух без подглядывания"),
        Task(4, "Запомнить сложные места")
    ),
    onTaskToggle: (Int, Boolean) -> Unit = { _, _ -> }
) {
    var taskList by remember { mutableStateOf(tasks) }

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Задачи на сегодня",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = PlayfairDisplay,
                    color = OliveDark
                )

                IconButton(onClick = { /* TODO: Add task */ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Добавить задачу",
                        tint = OliveDark
                    )
                }
            }

            Text(
                text = "Отметь выполненные — стрик зависит от всех",
                fontSize = 14.sp,
                color = Sage,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            val completedCount = taskList.count { it.isCompleted }
            val totalCount = taskList.size
            val progress = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f

            GlassmorphismCard {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Прогресс",
                            fontSize = 14.sp,
                            color = Sage
                        )
                        Text(
                            text = "$completedCount / $totalCount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OliveDark
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Beige)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(OliveMedium)
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            taskList.forEachIndexed { index, task ->
                TaskItem(
                    task = task,
                    onCheckedChange = { checked ->
                        taskList = taskList.map {
                            if (it.id == task.id) it.copy(isCompleted = checked) else it
                        }
                        onTaskToggle(task.id, checked)
                    }
                )

                if (index < taskList.lastIndex) {
                    Spacer(Modifier.height(12.dp))
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (task.isCompleted) Sage.copy(alpha = 0.12f) else Paper,
                RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LeafCheckbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange
        )

        Spacer(Modifier.width(14.dp))

        Text(
            text = task.title,
            fontSize = 16.sp,
            color = if (task.isCompleted) Sage else OliveDark,
            fontWeight = if (task.isCompleted) FontWeight.Normal else FontWeight.Medium
        )
    }
}
