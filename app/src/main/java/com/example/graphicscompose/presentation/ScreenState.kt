package com.example.graphicscompose.presentation

import com.example.graphicscompose.data.Bar

sealed class ScreenState {
    object Initial : ScreenState()

    object Loading : ScreenState()

    data class Content(val barList: List<Bar>, val timeFrame: TimeFrame) : ScreenState()
}