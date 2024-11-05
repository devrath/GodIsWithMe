package com.istudio.godiswithme.main

import androidx.lifecycle.ViewModel
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.istudio.godiswithme.main.MainContract.UiState
import com.istudio.godiswithme.main.MainContract.UiAction
import com.istudio.godiswithme.main.MainContract.SideEffect

class MainVm : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()



}

private fun initialUiState(): UiState = UiState