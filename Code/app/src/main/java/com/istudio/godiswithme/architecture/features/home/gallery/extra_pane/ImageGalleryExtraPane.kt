package com.istudio.godiswithme.architecture.features.home.gallery.extra_pane

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.features.home.gallery.main_pane.ImageGalleryMainPaneVm
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPaneContract.UiState
import com.istudio.godiswithme.common.mvi.unpack
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageGalleryExtraPane(modifier: Modifier = Modifier, godName: String) {
    val viewModel: ImageGalleryExtraPaneVm = koinViewModel()
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

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Details") }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val bitmapImage = uiState.godData?.godImageBitmap
            val name = uiState.godData?.godName.orEmpty()
            val godDescription = uiState.godData?.description.orEmpty()

            bitmapImage?.asImageBitmap()?.let { bitmp ->
                Image(
                    painter = BitmapPainter(bitmp),
                    contentDescription = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = godDescription,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }

}