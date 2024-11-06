package com.istudio.godiswithme.architecture.features.home.gods.godSongs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.architecture.domain.usecases.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.domain.usecases.GetGodSongsByNameUseCase
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiState
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.launch

class GodSongsVm(
    private val getGodSongsByNameUseCase: GetGodSongsByNameUseCase,
    private val logger: Logger
) : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadScreen -> lodGodData(uiAction.godName)
        }
    }


    private fun lodGodData(godName: String) {
        viewModelScope.launch {
            getGodSongsByNameUseCase.invoke(godName).collect { godData ->
                // Handle the list of gods here
                logger.d("result",godData.toString())
            }
        }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)