package com.istudio.godiswithme.architecture.features.home.gods.godSongs.composables.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.istudio.godiswithme.architecture.domain.models.Song

@Composable
fun SongsListItem(
    paddingValues: PaddingValues,
    data: List<Song>,
    onSongClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = paddingValues
    ) {
        itemsIndexed(data) { index, audio ->
            SongsListItem(
                audio = audio,
                onSongClick = { onSongClick(index) }
            )
        }
    }
}