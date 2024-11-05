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
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenContract.UiState
import com.istudio.godiswithme.common.mvi.unpack
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun GodSongsScreen(modifier: Modifier = Modifier, godName: String) {
    val viewModel: GodSongsVm = koinViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CurrentScreen(uiState, sideEffect, onAction, godName)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrentScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
    godName: String,
) {

    LaunchedEffect(Unit) { onAction(UiAction.LoadScreen(godName)) }

    Scaffold(topBar = { TopAppBar(title = { Text(text = godName) }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

        }
    }

}