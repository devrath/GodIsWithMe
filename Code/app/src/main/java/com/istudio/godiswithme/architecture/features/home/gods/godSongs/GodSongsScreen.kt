package com.istudio.godiswithme.architecture.features.home.gods.godSongs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.features.home.audio.AudioVm
import com.istudio.godiswithme.architecture.features.home.audio.UIEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun GodSongsScreen(
    modifier: Modifier = Modifier,
    godName: String,
    invokeAudioService : () -> Unit
) {
    val audioVm: AudioVm = koinViewModel()

    LaunchedEffect(Unit) { audioVm.loadAudioData(godName) }

    CurrentScreen(
        godName = godName,
        progress = audioVm.progress,
        onProgress = { audioVm.onUiEvents(UIEvents.SeekTo(it)) },
        isAudioPlaying = audioVm.isPlaying,
        audiList = audioVm.audioList,
        currentPlayingAudio = audioVm.currentSelectedAudio,
        onStart = {
            audioVm.onUiEvents(UIEvents.PlayPause)
        },
        onSongClick = {
            audioVm.onUiEvents(UIEvents.SelectedAudioChange(it))
            invokeAudioService()
        },
        onNext = {
            audioVm.onUiEvents(UIEvents.SeekToNext)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrentScreen(
    godName: String,
    progress: Float,
    onProgress: (Float) -> Unit,
    isAudioPlaying: Boolean,
    currentPlayingAudio: Song,
    onStart: () -> Unit,
    audiList: List<Song>,
    onSongClick: (Int) -> Unit,
    onNext: () -> Unit,
) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = godName) }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HomeScreen(
                progress = progress,
                onProgress = onProgress,
                isAudioPlaying = isAudioPlaying,
                audiList = audiList,
                currentPlayingAudio = currentPlayingAudio,
                onStart = onStart,
                onSongClick = onSongClick,
                onNext = onNext
            )
        }
    }

}