package com.istudio.godiswithme.architecture.features.home.gods.god

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiState
import com.istudio.godiswithme.common.mvi.unpack
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun GodScreen(
    modifier: Modifier = Modifier,
    godName: String,
    descriptionOnClick: (String) -> Unit,
    songsOnClick: (String) -> Unit
) {
    val viewModel: GodScreenVm = koinViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CurrentScreen(uiState, sideEffect, godName, onAction, descriptionOnClick, songsOnClick)
}

@Composable
private fun CurrentScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    godName: String,
    onAction: (UiAction) -> Unit,
    descriptionOnClick: (String) -> Unit,
    songsOnClick: (String) -> Unit,
) {

    LaunchedEffect(godName) { onAction(UiAction.LoadScreen(godName)) }

    Scaffold(floatingActionButton = {
       Row {
           FloatingActionButton(onClick = {
               songsOnClick.invoke(uiState.godData?.godName.orEmpty())
           }) {
               Icon(imageVector = Icons.Default.MusicNote, contentDescription = "Songs")
           }
           FloatingActionButton(onClick = {
               descriptionOnClick.invoke(uiState.godData?.godName.orEmpty())
           }) {
               Icon(imageVector = Icons.Default.Info, contentDescription = "Description")
           }
       }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            val bitmapImage = uiState.godData?.godImageBitmap
            bitmapImage?.asImageBitmap()?.let { bitmp ->
                Image(
                    painter = BitmapPainter(bitmp),
                    contentDescription = "Click for more information",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}