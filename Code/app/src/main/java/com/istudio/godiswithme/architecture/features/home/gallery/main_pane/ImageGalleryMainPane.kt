package com.istudio.godiswithme.architecture.features.home.gallery.main_pane

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.architecture.features.home.gallery.main_pane.ImageGalleryMainPaneContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gallery.main_pane.ImageGalleryMainPaneContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gallery.main_pane.ImageGalleryMainPaneContract.UiState
import com.istudio.godiswithme.common.mvi.CollectSideEffect
import com.istudio.godiswithme.common.mvi.unpack
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageGalleryMainPane(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    val viewModel: ImageGalleryMainPaneVm = koinViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CurrentScreen(uiState, sideEffect, onAction, onClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrentScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
    onClick: (String) -> Unit,
) {

    val context = LocalContext.current

    // Messages display
    CollectSideEffect(sideEffect) {
        when (it) {
            SideEffect.DisplayError -> {
                Toast.makeText(context, "Something Went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "God Images") }) }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 210.dp), // Set the minimum size for each cell
            modifier = Modifier.padding(it)
        ) {

            items(uiState.listOfGods) { godData ->
                val bitmapImage = godData.godImageUri
                if (bitmapImage != null) {
                    godData.godImageBitmap?.asImageBitmap()?.let { bitmp ->
                        Image(
                            painter = BitmapPainter(bitmp),
                            contentDescription = godData.godName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp)
                                .clickable {
                                    onClick.invoke(godData.godName)
                                }
                        )
                    }
                }
            }
        }
    }


}