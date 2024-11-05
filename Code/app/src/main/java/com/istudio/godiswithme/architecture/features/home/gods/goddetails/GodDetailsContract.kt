package com.istudio.godiswithme.architecture.features.home.gods.goddetails

import com.istudio.godiswithme.architecture.domain_entity.GodData

interface GodDetailsContract {
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