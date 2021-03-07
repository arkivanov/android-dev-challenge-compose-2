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
package com.example.androiddevchallenge

import android.os.SystemClock
import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.flipclock.FlipClock
import com.example.androiddevchallenge.ui.flipclock.FlipClockEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.ceil
import kotlin.math.max

@Composable
fun Flipper(onDarkThemeToggled: () -> Unit, modifier: Modifier = Modifier) {
    var endTime by remember { mutableStateOf(SystemClock.uptimeMillis()) }
    var remainingSeconds by remember { mutableStateOf(0) }

    fun updateRemainingTime() {
        remainingSeconds = ceil(max(endTime - SystemClock.uptimeMillis(), 0L).toFloat() / 1000F).toInt()
    }

    fun addTime(millis: Long) {
        endTime = max(endTime, SystemClock.uptimeMillis()) + millis
        updateRemainingTime()
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            while (true) {
                updateRemainingTime()
                delay(100L)
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(48.dp))

        FlipClock(
            seconds = remainingSeconds,
            endMillis = endTime,
            events = FlipClockEvents(
                onHoursIncrement = { addTime(DateUtils.HOUR_IN_MILLIS) },
                onHoursDecrement = { addTime(-DateUtils.HOUR_IN_MILLIS) },
                onMinutesIncrement = { addTime(DateUtils.MINUTE_IN_MILLIS) },
                onMinutesDecrement = { addTime(-DateUtils.MINUTE_IN_MILLIS) },
                onSecondsIncrement = { addTime(DateUtils.SECOND_IN_MILLIS) },
                onSecondsDecrement = { addTime(-DateUtils.SECOND_IN_MILLIS) }
            )
        )

        Spacer(modifier = Modifier.height(48.dp))

        IconButton(onClick = onDarkThemeToggled) {
            Icon(
                imageVector = if (MaterialTheme.colors.isLight) Icons.Default.DarkMode else Icons.Default.LightMode,
                contentDescription = "Dark theme button"
            )
        }
    }
}
