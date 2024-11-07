@file:OptIn(SavedStateHandleSaveableApi::class)

package com.istudio.godiswithme.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Main View model will be a shared view model which we shall use for communication from all the components
 */
class MainVm(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var isAudioServiceRunning by savedStateHandle.saveable { mutableStateOf(false) }

    private val _eventChannel = Channel<MainUiState>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

}

sealed class MainUiState {
    data object StartPlayerService : MainUiState()
}