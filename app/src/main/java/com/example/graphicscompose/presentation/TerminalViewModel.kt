package com.example.graphicscompose.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphicscompose.data.ApiFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TerminalViewModel: ViewModel() {

    private val apiService = ApiFactory.apiService

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler {_, throwable ->
        Log.d("TerminalViewModel", "exceptionHandler: $throwable")
    }

    init {
        loadBarList()
    }

    private fun loadBarList() {
        viewModelScope.launch(exceptionHandler) {
            val barList = apiService.loadBars().barList
            _state.value = ScreenState.Content(barList = barList)
        }
    }
}