package com.istudio.godiswithme.architecture.features.home.gods.godgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.architecture.domain.repository.LanguageRepository
import com.istudio.godiswithme.architecture.domain.usecases.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.domain.usecases.GetGodsListUseCase
import com.istudio.godiswithme.common.mvi.MVI
import com.istudio.godiswithme.common.mvi.mvi
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiState
import kotlinx.coroutines.launch
import java.util.Locale

class GodGalleryVm(
    private val getGodsListUseCase: GetGodsListUseCase,
    private val logger: Logger,
    private val languageRepository: LanguageRepository
) : ViewModel() , MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    init {
        // Load initial data or handle any business logic
        viewModelScope.launch {
            val input = GetGodsListUseCase.Param(languageCode = languageRepository.getLanguageCode())
            getGodsListUseCase.invoke(input).collect { godsList ->
                // Handle the list of gods here
                logger.d("result",godsList.toString())
                updateUiState { copy(listOfGods = godsList) }
            }
        }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)