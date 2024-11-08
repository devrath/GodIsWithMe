package com.istudio.godiswithme.architecture.features.home.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.application.APP_TAG
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.SideEffect
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
            initSettingsScreen()
        }
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.UpdateLanguageSelectionState -> {
                logger.d(APP_TAG,"Language selection state:-> ${uiAction.isDisplayed}")
                updateUiState { copy(isLanguageSelectionDisplayed = uiAction.isDisplayed) }
            }
        }
    }

    private fun initSettingsScreen() = runCatching {
        val currentLanguageCode = locale.language
        val currentLanguageName = AppLanguage.fromCode(currentLanguageCode)
        logger.d(APP_TAG,"Current Language code ----> $currentLanguageCode")
        logger.d(APP_TAG,"Current Language name ----> ${currentLanguageName.displayName}")
        updateUiState { copy(language = currentLanguageName) }
    }.getOrElse {
        logger.d(APP_TAG,"Exception occurred while initializing the language")
        updateUiState { copy(language = AppLanguage.English) }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true)