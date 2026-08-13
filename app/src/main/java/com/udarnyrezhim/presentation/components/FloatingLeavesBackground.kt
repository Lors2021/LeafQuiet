package com.udarnyrezhim.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.udarnyrezhim.R
import kotlin.random.Random

data class LeafData(
    val size: Int,
    val color: Color,
    val startX: Float,
    val endX: Float,
    val startY: Float,
    val endY: Float,
    val durationX: Int,
    val durationY: Int,
    val durationRot: Int,
    val durationAlpha: Int
) {
    companion object {
        fun random() = LeafData(
            size = Random.nextInt(20, 40),
            color = listOf(
                Color(0xFF9CAF88).copy(alpha = 0.35f),
                Color(0xFFB8C9A9).copy(alpha = 0.25f),
                Color(0xFF6B7F3E).copy(alpha = 0.2f)
            ).random(),
            startX = Random.nextFloat() * 400f - 200f,
            endX = Random.nextFloat() * 400f - 200f,
            startY = Random.nextFloat() * 800f - 400f,
            endY = Random.nextFloat() * 800f - 400f,
            durationX = Random.nextInt(15000, 20000),
            durationY = Random.nextInt(18000, 25000),
            durationRot = Random.nextInt(12000, 18000),
            durationAlpha = Random.nextInt(10000, 15000)
        )
    }
}

@Composable
fun FloatingLeavesBackground(leafCount: Int = 6) {
    val leaves = remember { List(leafCount) { LeafData.random() } }

    Box(modifier = Modifier.fillMaxSize().zIndex(-1f)) {
        leaves.forEach { leaf ->
            val infiniteTransition = rememberInfiniteTransition(label = "leaf")

            val offsetY by infiniteTransition.animateFloat(
                initialValue = leaf.startY,
                targetValue = leaf.endY,
                animationSpec = infiniteRepeatable(
                    tween(leaf.durationY, easing = EaseInOutSine),
                    RepeatMode.Reverse
                ),
                label = "y"
            )
            val offsetX by infiniteTransition.animateFloat(
                initialValue = leaf.startX,
                targetValue = leaf.endX,
                animationSpec = infiniteRepeatable(
                    tween(leaf.durationX, easing = EaseInOutSine),
                    RepeatMode.Reverse
                ),
                label = "x"
            )
            val rotation by infiniteTransition.animateFloat(
                initialValue = -10f,
                targetValue = 10f,
                animationSpec = infiniteRepeatable(
                    tween(leaf.durationRot, easing = EaseInOutSine),
                    RepeatMode.Reverse
                ),
                label = "rot"
            )
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.2f,
                targetValue = 0.4f,
                animationSpec = infiniteRepeatable(
                    tween(leaf.durationAlpha, easing = EaseInOutSine),
                    RepeatMode.Reverse
                ),
                label = "alpha"
            )

            Image(
                painter = painterResource(R.drawable.leaf),
                contentDescription = null,
                modifier = Modifier
                    .size(leaf.size.dp)
                    .offset(offsetX.dp, offsetY.dp)
                    .rotate(rotation)
                    .alpha(alpha),
                colorFilter = ColorFilter.tint(leaf.color)
            )
        }
    }
}
