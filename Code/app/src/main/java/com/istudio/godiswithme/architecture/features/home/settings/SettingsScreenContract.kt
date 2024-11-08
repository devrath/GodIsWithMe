package com.istudio.godiswithme.architecture.features.home.settings

interface SettingsScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
        val isLanguageSelectionDisplayed: Boolean = false,
        val language: AppLanguage = AppLanguage.English
    )

    sealed interface UiAction {
        data class UpdateLanguageSelectionState(val isDisplayed: Boolean) : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}