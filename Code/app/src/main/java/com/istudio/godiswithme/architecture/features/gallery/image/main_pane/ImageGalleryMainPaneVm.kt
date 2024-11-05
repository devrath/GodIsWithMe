package com.istudio.godiswithme.architecture.features.gallery.image.main_pane

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.application.MY_APPLICATON_LOGS
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.architecture.features.gallery.image.main_pane.ImageGalleryMainPaneContract.SideEffect
import com.istudio.godiswithme.architecture.features.gallery.image.main_pane.ImageGalleryMainPaneContract.UiAction
import com.istudio.godiswithme.architecture.features.gallery.image.main_pane.ImageGalleryMainPaneContract.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageGalleryMainPaneVm(
    private val getGodsListUseCase: GetGodsListUseCase,
    private val assetManager: AssetManager,
    private val logger: Logger
) : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        // Load initial data or handle any business logic
        viewModelScope.launch {
            getGodsListUseCase.invoke().collect { godsList ->
                // Handle the list of gods here
                logger.d("result",godsList.toString())
                updateUiState { copy(listOfGods = godsList) }
            }
        }
    }

    fun loadImages() {
        // Logic to load images
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)