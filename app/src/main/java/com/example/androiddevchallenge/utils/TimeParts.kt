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
package com.example.androiddevchallenge.utils

fun getTimeParts(seconds: Int): TimeParts {
    val partHours = seconds / 60 / 60
    val partMinutes = (seconds - partHours * 60 * 60) / 60
    val partSeconds = seconds - partHours * 60 * 60 - partMinutes * 60

    return TimeParts(
        hours = partHours,
        minutes = partMinutes,
        seconds = partSeconds
    )
}

class TimeParts(
    val hours: Int,
    val minutes: Int,
    val seconds: Int
)
