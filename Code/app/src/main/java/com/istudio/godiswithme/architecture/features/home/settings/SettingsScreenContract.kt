package com.istudio.godiswithme.architecture.features.home.settings

interface SettingsScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
        val isLanguageSelectionDisplayed: Boolean = false,
        val selectedLanguage: AppLanguage = AppLanguage.English,
        val languages : List<AppLanguage>
    )

    sealed interface UiAction {
        data class UpdateLanguageSelectionState(
            val isDisplayed: Boolean,
            val language: AppLanguage
        ) : UiAction
        data class UserUpdatedLanguage(
            val isDisplayed: Boolean,
            val language: AppLanguage
        ) : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
        data class LanguageUpdated(val language: AppLanguage) : SideEffect
    }
}