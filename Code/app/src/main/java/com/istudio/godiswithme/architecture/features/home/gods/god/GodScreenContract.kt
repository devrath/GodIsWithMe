package com.istudio.godiswithme.architecture.features.home.gods.god

import com.istudio.godiswithme.architecture.domain_entity.GodData

interface GodScreenContract {
    data class UiState(
        val isLoading: Boolean = true,
        val godData: GodData? = null,
    )

    sealed interface UiAction {
        data class LoadScreen(val godName: String) : UiAction
    }

    sealed interface SideEffect {
        data object DisplayError : SideEffect
    }
}