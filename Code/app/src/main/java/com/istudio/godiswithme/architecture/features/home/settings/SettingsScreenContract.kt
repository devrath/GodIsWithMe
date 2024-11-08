package com.istudio.godiswithme.architecture.features.home.settings

interface SettingsScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
    )

    sealed interface UiAction {
        //data object OnIncreaseCountClick : UiAction
        //data object OnDecreaseCountClick : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}