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
package com.example.androiddevchallenge.ui.flipclock

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.animateFloatAsState
import com.example.androiddevchallenge.utils.getTimeParts
import kotlin.math.ceil

@Composable
fun FlipClock(
    seconds: Int,
    endMillis: Long,
    events: FlipClockEvents
) {
    val animatedSeconds by animateFloatAsState(key = endMillis, targetValue = seconds.toFloat())

    val currentSeconds = ceil(animatedSeconds).toInt()
    val nextSeconds = currentSeconds - 1
    val factor = currentSeconds.toFloat() - animatedSeconds
    val currentParts = getTimeParts(currentSeconds)
    val nextParts = getTimeParts(nextSeconds)

    Row {
        FlapSection(
            title = stringResource(R.string.hours),
            currentValue = currentParts.hours,
            nextValue = nextParts.hours,
            factor = if (currentParts.hours == nextParts.hours) 0F else factor,
            onIncrement = events.onHoursIncrement,
            onDecrement = events.onHoursDecrement
        )

        Spacer(modifier = Modifier.width(16.dp))

        FlapSection(
            title = stringResource(R.string.minutes),
            currentValue = currentParts.minutes,
            nextValue = nextParts.minutes,
            factor = if (currentParts.minutes == nextParts.minutes) 0F else factor,
            onIncrement = events.onMinutesIncrement,
            onDecrement = events.onMinutesDecrement
        )

        Spacer(modifier = Modifier.width(16.dp))

        FlapSection(
            title = stringResource(R.string.seconds),
            currentValue = currentParts.seconds,
            nextValue = nextParts.seconds,
            factor = if (currentParts.seconds == nextParts.seconds) 0F else factor,
            onIncrement = events.onSecondsIncrement,
            onDecrement = events.onSecondsDecrement
        )
    }
}

class FlipClockEvents(
    val onHoursIncrement: () -> Unit,
    val onHoursDecrement: () -> Unit,
    val onMinutesIncrement: () -> Unit,
    val onMinutesDecrement: () -> Unit,
    val onSecondsIncrement: () -> Unit,
    val onSecondsDecrement: () -> Unit,
)
