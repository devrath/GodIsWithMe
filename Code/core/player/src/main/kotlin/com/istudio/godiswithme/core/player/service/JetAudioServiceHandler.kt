package com.istudio.godiswithme.core.player.service

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.AssetDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * This class helps to manage audio playback using exoplayer
 * <*> The class handles audio playback events
 * <*> The states of the player
 * <*> Progress Updates
 */
@androidx.annotation.OptIn(UnstableApi::class)
class JetAudioServiceHandler(
    private val exoPlayer: ExoPlayer,
    private val assetDataSource: AssetDataSource,
) : Player.Listener {

    private val _audioState: MutableStateFlow<JetAudioState> =
        MutableStateFlow(JetAudioState.Initial)
    val audioState: StateFlow<JetAudioState> = _audioState.asStateFlow()

    private var job: Job? = null

    init {
        exoPlayer.addListener(this)
    }

    /**
     * We set the media source from locally for one media item
     */
    fun addMediaItem(mediaItem: MediaItem) {
        exoPlayer.apply {
            setMediaSource(prepareProgressiveMediaSource(mediaItem))
            prepare()
        }
    }

    /**
     * We set the media sources from locally from a list of media items
     */
    fun setMediaItemList(mediaItems: List<MediaItem>) {
        mediaItems.map {
            prepareProgressiveMediaSource(it)
        }.also { sources ->
            exoPlayer.apply {
                setMediaSources(sources)
                prepare()
            }
        }
    }

    private fun prepareProgressiveMediaSource(mediaItem: MediaItem) =
        ProgressiveMediaSource.Factory { assetDataSource }
            .createMediaSource(mediaItem)

    suspend fun onPlayerEvents(
        playerEvent: PlayerEvent,
        selectedAudioIndex: Int = -1,
        seekPosition: Long = 0,
    ) {
        when (playerEvent) {
            is PlayerEvent.Backward -> exoPlayer.seekBack()
            is PlayerEvent.Forward -> exoPlayer.seekForward()
            is PlayerEvent.SeekToNext -> exoPlayer.seekToNext()
            is PlayerEvent.PlayPause -> playOrPause()
            is PlayerEvent.SeekTo -> exoPlayer.seekTo(seekPosition)
            is PlayerEvent.SelectedAudioChange -> {
                when (selectedAudioIndex) {
                    exoPlayer.currentMediaItemIndex -> {
                        playOrPause()
                    }

                    else -> {
                        exoPlayer.seekToDefaultPosition(selectedAudioIndex)
                        _audioState.value = JetAudioState.Playing(
                            isPlaying = true
                        )
                        exoPlayer.playWhenReady = true
                        startProgressUpdate()
                    }
                }
            }

            is PlayerEvent.Stop -> stopProgressUpdate()
            is PlayerEvent.UpdateProgress -> {
                exoPlayer.seekTo(
                    (exoPlayer.duration * playerEvent.newProgress).toLong()
                )
            }
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_BUFFERING -> _audioState.value =
                JetAudioState.Buffering(exoPlayer.currentPosition)

            ExoPlayer.STATE_READY -> _audioState.value =
                JetAudioState.Ready(exoPlayer.duration)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _audioState.value = JetAudioState.Playing(isPlaying = isPlaying)
        _audioState.value = JetAudioState.CurrentPlaying(exoPlayer.currentMediaItemIndex)
        if (isPlaying) {
            GlobalScope.launch(Dispatchers.Main) {
                startProgressUpdate()
            }
        } else {
            stopProgressUpdate()
        }
    }

    private suspend fun playOrPause(){
        when {
            exoPlayer.isPlaying -> {
                // Media is playing ----> Pause the Media
                exoPlayer.pause()
                stopProgressUpdate()
            }
            else -> {
                // Media is paused ----> Play the Media
                exoPlayer.play()
                // Set the state that player is playing
                _audioState.value = JetAudioState.Playing(isPlaying = true)
                startProgressUpdate()
            }
        }
    }


    private suspend fun startProgressUpdate() = job.run {
        while (true) {
            delay(500)
            // Set the state of the progress with a value
            _audioState.value = JetAudioState.Progress(exoPlayer.contentPosition)
        }
    }


    private fun stopProgressUpdate(){
        exoPlayer.pause()
        // Cancel the player job
        job?.cancel()
        // Set the state that player is not playing
        _audioState.value = JetAudioState.Playing(isPlaying = false)
    }

}

sealed class PlayerEvent {
    data object PlayPause : PlayerEvent()
    data object SelectedAudioChange : PlayerEvent()
    data object Backward : PlayerEvent()
    data object SeekToNext : PlayerEvent()
    data object Forward : PlayerEvent()
    data object SeekTo : PlayerEvent()
    data object Stop : PlayerEvent()
    data class UpdateProgress(val newProgress: Float) : PlayerEvent()
}

sealed class JetAudioState {
    data object Initial : JetAudioState()
    data class Ready(val duration: Long) : JetAudioState()
    data class Progress(val progress: Long) : JetAudioState()
    data class Buffering(val progress: Long) : JetAudioState()
    data class Playing(val isPlaying: Boolean) : JetAudioState()
    data class CurrentPlaying(val mediaItemIndex: Int) : JetAudioState()
}