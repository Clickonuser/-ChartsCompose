package com.example.graphicscompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphicscompose.data.ApiFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TerminalViewModel : ViewModel() {

    private val apiService = ApiFactory.apiService

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val state = _state.asStateFlow()

    private var lastState: ScreenState = ScreenState.Initial

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = lastState
    }

    init {
        loadBarList()
    }

    fun loadBarList(timeFrame: TimeFrame = TimeFrame.HOUR_1) {
        lastState = _state.value
        _state.value = ScreenState.Loading
        viewModelScope.launch(exceptionHandler) {
            val barList = apiService.loadBars(timeFrame.value).barList
            _state.value = ScreenState.Content(barList = barList, timeFrame = timeFrame)
        }
    }
}