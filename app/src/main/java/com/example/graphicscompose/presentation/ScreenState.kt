package com.example.graphicscompose.presentation

import com.example.graphicscompose.data.Bar

sealed class ScreenState {
    object Initial: ScreenState()
    data class Content(val barList: List<Bar>): ScreenState()
}