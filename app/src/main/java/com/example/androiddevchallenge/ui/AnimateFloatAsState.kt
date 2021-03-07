/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.isFinished
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember

@Composable
fun animateFloatAsState(key: Any?, targetValue: Float): State<Float> {
    val animSpec = remember { spring<Float>(stiffness = Spring.StiffnessLow) }
    val animationState = remember(key) { AnimationState(targetValue) }

    LaunchedEffect(key, targetValue, animSpec) {
        animationState.animateTo(
            targetValue = targetValue,
            animationSpec = animSpec,
            sequentialAnimation = !animationState.isFinished
        )
    }

    return animationState
}
