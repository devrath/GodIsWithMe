package com.istudio.godiswithme.architecture.features.home.gallery.extra_pane

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.architecture.domain.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.UiState
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.launch

class ImageGalleryExtraPaneVm(
    private val getGodByNameUseCase: GetGodByNameUseCase,
    private val logger: Logger
) : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadScreen -> lodGodData(uiAction.godName)
        }
    }


    private fun lodGodData(godName: String) {
        viewModelScope.launch {
            getGodByNameUseCase.invoke(godName).collect { godData ->
                // Handle the list of gods here
                logger.d("result",godData.toString())
                updateUiState { copy(godData = godData) }
            }
        }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)