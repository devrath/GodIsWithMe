package com.istudio.godiswithme.architecture.features.home.settings

import com.istudio.godiswithme.architecture.data.services.language.AppLanguage

interface SettingsScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
        val isLanguageSelectionDisplayed: Boolean = false,
        val selectedLanguage: AppLanguage = AppLanguage.English,
        val languages : List<AppLanguage>
    )

    sealed interface UiAction {
        data class UserUpdatedLanguage(val language: AppLanguage) : UiAction
        data class LanguageSelectionDismissed(val isDisplayed: Boolean) : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
        data class LanguageUpdated(val language: AppLanguage) : SideEffect
    }
}