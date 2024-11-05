package com.istudio.godiswithme.main

interface MainContract {
    //data class UiState(val count: Int)
    data object UiState

    sealed interface UiAction {
        //data object OnIncreaseCountClick : UiAction
        //data object OnDecreaseCountClick : UiAction
    }

    sealed interface SideEffect {
        //data object ShowCountCanNotBeNegativeToast : SideEffect
    }
}