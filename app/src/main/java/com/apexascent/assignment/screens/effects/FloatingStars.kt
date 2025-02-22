package com.apexascent.assignment.screens.effects

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apexascent.assignment.R
import kotlinx.coroutines.launch

@Composable
fun FloatingStars() {
    val scope = rememberCoroutineScope()
    val starCount = 100
    // Generating a list of starData
    val stars = remember { List(starCount) { StarData() } }

    Box(modifier = Modifier.fillMaxSize()) {
        stars.forEach { star ->
            val offsetX = remember { Animatable(star.initialX) }
            val offsetY = remember { Animatable(star.initialY) }


            LaunchedEffect(star) {
                scope.launch {
                    while (true) {
                        offsetX.animateTo(
                            targetValue = star.targetX,
                            animationSpec = tween(durationMillis = star.duration, easing = LinearEasing)
                        )
                        offsetY.animateTo(
                            targetValue = star.targetY,
                            animationSpec = tween(durationMillis = star.duration, easing = LinearEasing)
                        )
                        star.resetTargets()
                    }
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.snow_flake),
                contentDescription = null,
                tint = Color.White.copy(alpha = star.alpha),
                modifier = Modifier
                    .offset(offsetX.value.dp, offsetY.value.dp)
                    .size(star.size.dp)
            )

        }
    }
}
// A data class to generate random values of the star sizes, positions, and duration of animation so they look random in the background
data class StarData(
    val initialX: Float = (-100..100).random().toFloat(),
    val initialY: Float = (-100..100).random().toFloat(),
    var targetX: Float = (0..1000).random().toFloat(),
    var targetY: Float = (0..1000).random().toFloat(),
    val duration: Int = (2000..5000).random(),
    val size: Int = (5..20).random(),
    val alpha: Float = 0.5f
) {
    // To change the target position every time the animation is done to prevent the background stars from stopping suddenly
    fun resetTargets() {
        targetX = (0..1000).random().toFloat()
        targetY = (0..1000).random().toFloat()
    }
}