package com.udarnyrezhim.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.udarnyrezhim.presentation.theme.OliveMedium

@Composable
fun LeafCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(checked) {
        if (checked) {
            scale.animateTo(1.2f, spring(Spring.DampingRatioLowBouncy))
            scale.animateTo(1f, spring(Spring.DampingRatioMediumBouncy))
        }
    }

    Box(
        modifier = modifier
            .size(24.dp)
            .scale(scale.value)
            .background(
                if (checked) OliveMedium else Color.Transparent,
                RoundedCornerShape(6.dp)
            )
            .border(2.dp, OliveMedium, RoundedCornerShape(6.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}
