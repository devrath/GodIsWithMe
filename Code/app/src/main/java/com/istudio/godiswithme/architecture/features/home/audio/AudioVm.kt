package com.istudio.godiswithme.architecture.features.home.audio

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.core.player.service.JetAudioServiceHandler

class AudioVm(
    private val audioServiceHandler: JetAudioServiceHandler,
    private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


}

sealed class UIEvents {
    data object PlayPause : UIEvents()
    data class SelectedAudioChange(val index: Int) : UIEvents()
    data class SeekTo(val position: Float) : UIEvents()
    data object SeekToNext : UIEvents()
    data object Backward : UIEvents()
    data object Forward : UIEvents()
    data class UpdateProgress(val newProgress: Float) : UIEvents()
}

sealed class UIState {
    data object Initial : UIState()
    data object Ready : UIState()
}