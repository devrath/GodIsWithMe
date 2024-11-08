package com.istudio.godiswithme.architecture.features.home.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.SideEffect
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.launch
import java.util.Locale

class SettingsScreenVm(
    private val logger: Logger,
    private val locale: Locale
) : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        viewModelScope.launch {

        }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)