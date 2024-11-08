@file:OptIn(SavedStateHandleSaveableApi::class)

package com.istudio.godiswithme.architecture.features.home.audio

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.domain.usecases.GetGodSongsByNameUseCase
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.core.player.service.JetAudioServiceHandler
import com.istudio.godiswithme.core.player.service.JetAudioState
import com.istudio.godiswithme.core.player.service.PlayerEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


private val initialAudio = Song(songName = "", songLocation = "")

class AudioVm(
    private val getGodSongsByNameUseCase: GetGodSongsByNameUseCase,
    private val audioServiceHandler: JetAudioServiceHandler,
    private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var duration by savedStateHandle.saveable { mutableLongStateOf(0L) }
    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
    var currentSelectedAudio by savedStateHandle.saveable { mutableStateOf(initialAudio) }
    var audioList by savedStateHandle.saveable { mutableStateOf(emptyList<Song>()) }

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        observeAudioState()
    }

    private fun observeAudioState() = viewModelScope.launch {
        audioServiceHandler.audioState.collectLatest { mediaState ->
            handleMediaState(mediaState)
        }
    }

    private fun handleMediaState(mediaState: JetAudioState) {
        when (mediaState) {
            is JetAudioState.Initial -> _uiState.value = UIState.Initial
            is JetAudioState.Playing -> isPlaying = mediaState.isPlaying
            is JetAudioState.Buffering -> calculateProgressValue(mediaState.progress)
            is JetAudioState.Progress -> calculateProgressValue(mediaState.progress)
            is JetAudioState.CurrentPlaying -> updateCurrentSelectedAudio(mediaState.mediaItemIndex)
            is JetAudioState.Ready -> {
                duration = mediaState.duration
                _uiState.value = UIState.Ready
            }
        }
    }

    private fun updateCurrentSelectedAudio(index: Int) {
        currentSelectedAudio = audioList.getOrNull(index) ?: initialAudio
    }

    fun loadAudioData(godName: String) = viewModelScope.launch {
        getGodSongsByNameUseCase(godName).collect { godData ->
            logger.d("result", godData.toString())
            audioList = godData
            setMediaItems()
        }
    }

    private fun calculateProgressValue(currentProgress: Long) {
        progress = if (currentProgress > 0) {
            (currentProgress.toFloat() / duration * 100f)
        } else 0f
    }

    private fun setMediaItems() {
        val mediaItems = audioList.map { MediaItem.fromUri(Uri.parse(it.songLocation)) }
        audioServiceHandler.setMediaItemList(mediaItems)
    }

    fun onUiEvents(uiEvents: UIEvents) = viewModelScope.launch {
        handleUiEvents(uiEvents)
    }

    private suspend fun handleUiEvents(uiEvents: UIEvents) {
        audioServiceHandler.apply {
            when (uiEvents) {
                is UIEvents.PlayPause -> onPlayerEvents(PlayerEvent.PlayPause)
                is UIEvents.SeekTo -> onPlayerEvents(
                    PlayerEvent.SeekTo,
                    seekPosition = calculateSeekPosition(uiEvents.position)
                )
                is UIEvents.SelectedAudioChange -> onPlayerEvents(
                    PlayerEvent.SelectedAudioChange,
                    selectedAudioIndex = uiEvents.index
                )
                is UIEvents.UpdateProgress -> {
                    onPlayerEvents(PlayerEvent.UpdateProgress(uiEvents.newProgress))
                    progress = uiEvents.newProgress
                }
                is UIEvents.SeekToNext -> onPlayerEvents(PlayerEvent.SeekToNext)
                is UIEvents.Backward -> onPlayerEvents(PlayerEvent.Backward)
                is UIEvents.Forward -> onPlayerEvents(PlayerEvent.Forward)
            }
        }
    }

    private fun calculateSeekPosition(position: Float) =
        ((duration * position) / 100f).toLong()

    @SuppressLint("DefaultLocale")
    private fun formatDuration(duration: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            audioServiceHandler.onPlayerEvents(PlayerEvent.Stop)
        }
    }
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
