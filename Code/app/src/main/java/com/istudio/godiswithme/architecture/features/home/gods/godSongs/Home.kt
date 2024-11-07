package com.istudio.godiswithme.architecture.features.home.gods.godSongs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.features.home.gods.godSongs.composables.list.SongsListItem
import com.istudio.godiswithme.architecture.features.home.gods.godSongs.composables.player.BottomBarPlayer
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import kotlin.math.floor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    progress: Float,
    onProgress: (Float) -> Unit,
    isAudioPlaying: Boolean,
    currentPlayingAudio: Song,
    audiList: List<Song>,
    onStart: () -> Unit,
    onSongClick: (Int) -> Unit,
    onNext: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomBarPlayer(
                progress = progress,
                onProgress = onProgress,
                audio = currentPlayingAudio,
                onStart = onStart,
                onNext = onNext,
                isAudioPlaying = isAudioPlaying
            )
        }
    ) {
        SongsListItem(paddingValues = it, data = audiList, onSongClick)
    }

}

private fun timeStampToDuration(position: Long): String {
    val totalSecond = floor(position / 1E3).toInt()
    val minutes = totalSecond / 60
    val remainingSeconds = totalSecond - (minutes * 60)
    return if (position < 0) "--:--"
    else "%d:%02d".format(minutes, remainingSeconds)
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPrev() {
    GodIsWithMeTheme {
        HomeScreen(
            progress = 50f,
            onProgress = {},
            isAudioPlaying = true,
            audiList = listOf(
                Song(songName = "Name-1", songLocation = ""),
                Song(songName = "Name-2", songLocation = ""),
            ),
            currentPlayingAudio = Song(songName = "Name-1", songLocation = ""),
            onStart = {},
            onSongClick = {},
            onNext = {}
        )
    }
}