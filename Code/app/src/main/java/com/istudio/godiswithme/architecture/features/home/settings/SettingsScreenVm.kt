package com.istudio.godiswithme.architecture.features.home.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.application.APP_TAG
import com.istudio.godiswithme.architecture.data.services.language.AppLanguage
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
            // User made a new selection from list of languages from bottom sheet
            is UiAction.UserUpdatedLanguage -> {
                logger.d(APP_TAG, "User selected language:-> ${uiAction.language}")
                // Update the view model state
                updateUiState { copy(selectedLanguage = uiAction.language) }
                // Emit a side-effect making the the activity to restart
                viewModelScope.emitSideEffect(SideEffect.LanguageUpdated(uiAction.language))
            }

            // User has clicked dismiss of language list
            is UiAction.LanguageSelectionDismissed -> {
                updateUiState { copy(isLanguageSelectionDisplayed = uiAction.isDisplayed) }
            }
        }
    }

    private fun initSettingsScreen() = runCatching {
        val currentLanguageCode = locale.language
        val currentLanguageName = AppLanguage.fromCode(currentLanguageCode)
        logger.d(APP_TAG, "Current Language code ----> $currentLanguageCode")
        logger.d(APP_TAG, "Current Language name ----> ${currentLanguageName.displayName}")
        updateUiState {
            copy(
                selectedLanguage = currentLanguageName,
                languages = AppLanguage.getAllLanguages()
            )
        }
    }.getOrElse {
        logger.d(APP_TAG, "Exception occurred while initializing the language")
        updateUiState { copy(selectedLanguage = AppLanguage.English) }
    }

}

private fun initialUiState(): UiState = UiState(isLoading = true, languages = AppLanguage.getAllLanguages())