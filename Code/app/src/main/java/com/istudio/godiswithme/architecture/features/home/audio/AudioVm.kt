@file:OptIn(SavedStateHandleSaveableApi::class)

package com.istudio.godiswithme.architecture.features.home.audio

import android.annotation.SuppressLint
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


private val initialAudio = Song(songName = "", songLocation = "", songLocationUri = null)

class AudioVm(
    private val getGodSongsByNameUseCase: GetGodSongsByNameUseCase,
    private val audioServiceHandler: JetAudioServiceHandler,
    private val logger: Logger,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var duration by savedStateHandle.saveable{ mutableLongStateOf(0L) }
    var progress by savedStateHandle.saveable{ mutableFloatStateOf(0f) }
    var progressString by savedStateHandle.saveable{ mutableStateOf("00:00") }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
    var currentSelectedAudio by savedStateHandle.saveable { mutableStateOf(initialAudio) }
    var audioList by savedStateHandle.saveable { mutableStateOf(listOf<Song>()) }

    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Initial)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            audioServiceHandler.audioState.collectLatest { mediaState ->
                when (mediaState) {
                    is JetAudioState.Initial -> _uiState.value = UIState.Initial
                    is JetAudioState.Playing -> isPlaying = mediaState.isPlaying
                    is JetAudioState.Buffering -> calculateProgressValue(mediaState.progress)
                    is JetAudioState.Progress -> calculateProgressValue(mediaState.progress)
                    is JetAudioState.CurrentPlaying ->  currentSelectedAudio = audioList[mediaState.mediaItemIndex]
                    is JetAudioState.Ready ->  {
                        duration = mediaState.duration
                        _uiState.value = UIState.Ready
                    }
                }
            }
        }
    }

    fun loadAudioData(godName: String) {
        viewModelScope.launch {
            getGodSongsByNameUseCase.invoke(godName).collect { godData ->
                logger.d("result",godData.toString())
                audioList = godData
                setMediaItems()
            }
        }
    }

    private fun calculateProgressValue(currentProgress: Long) {
        progress =
            if (currentProgress > 0) ((currentProgress.toFloat() / duration.toFloat()) * 100f)
            else 0f
        progressString = formatDuration(currentProgress)
    }

    private fun setMediaItems() {
        audioList.map { audio ->
            MediaItem.Builder()
                .setUri(audio.songLocationUri)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setDisplayTitle(audio.songName)
                        .build()
                )
                .build()
        }.also {
            audioServiceHandler.setMediaItemList(it)
        }
    }

    fun onUiEvents(uiEvents: UIEvents) = viewModelScope.launch {
        // Communicate to the audioService handler so it upd
        audioServiceHandler.apply {
            when (uiEvents) {
                is UIEvents.Backward -> onPlayerEvents(PlayerEvent.Backward)
                is UIEvents.Forward -> onPlayerEvents(PlayerEvent.Forward)
                is UIEvents.SeekToNext -> onPlayerEvents(PlayerEvent.SeekToNext)
                is UIEvents.PlayPause -> onPlayerEvents(PlayerEvent.PlayPause)

                is UIEvents.SeekTo -> {
                    onPlayerEvents(
                        PlayerEvent.SeekTo,
                        seekPosition = ((duration * uiEvents.position) / 100f).toLong()
                    )
                }

                is UIEvents.SelectedAudioChange -> {
                    onPlayerEvents(
                        PlayerEvent.SelectedAudioChange,
                        selectedAudioIndex = uiEvents.index
                    )
                }

                is UIEvents.UpdateProgress -> {
                    onPlayerEvents(PlayerEvent.UpdateProgress(uiEvents.newProgress))
                    progress = uiEvents.newProgress
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    fun formatDuration(duration: Long): String {
        val minute = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds = (minute) - minute * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)
        return String.format("%02d:%02d", minute, seconds)
    }

    override fun onCleared() {
        viewModelScope.launch {
            audioServiceHandler.onPlayerEvents(PlayerEvent.Stop)
        }
        super.onCleared()
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