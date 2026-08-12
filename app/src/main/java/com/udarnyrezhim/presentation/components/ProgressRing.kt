package com.udarnyrezhim.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udarnyrezhim.presentation.theme.OliveDark
import com.udarnyrezhim.presentation.theme.OliveMedium
import com.udarnyrezhim.presentation.theme.PlayfairDisplay
import com.udarnyrezhim.presentation.theme.Sage

@Composable
fun ProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    strokeWidth: Dp = 12.dp,
    centerContent: @Composable () -> Unit = {}
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(1500, easing = FastOutSlowInEasing),
        label = "progress"
    )

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            val strokePx = strokeWidth.toPx()
            val arcSize = size.toPx() - strokePx

            drawArc(
                color = Color(0xFFE8E0D0),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokePx, cap = StrokeCap.Round),
                topLeft = androidx.compose.ui.geometry.Offset(strokePx / 2, strokePx / 2),
                size = androidx.compose.ui.geometry.Size(arcSize, arcSize)
            )

            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(OliveMedium, Sage),
                    center = androidx.compose.ui.geometry.Offset(size.toPx() / 2, size.toPx() / 2)
                ),
                startAngle = -90f,
                sweepAngle = animatedProgress * 360f,
                useCenter = false,
                style = Stroke(strokePx, cap = StrokeCap.Round),
                topLeft = androidx.compose.ui.geometry.Offset(strokePx / 2, strokePx / 2),
                size = androidx.compose.ui.geometry.Size(arcSize, arcSize)
            )
        }
        centerContent()
    }
}

@Composable
fun StreakCenterContent(currentStreak: Int, bestStreak: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Текущий стрик", fontSize = 12.sp, color = Sage)
        Text(
            "$currentStreak",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = OliveDark,
            fontFamily = PlayfairDisplay
        )
        Text("дней", fontSize = 14.sp, color = OliveMedium)
        Text("Лучший: $bestStreak", fontSize = 12.sp, color = Sage)
    }
}
