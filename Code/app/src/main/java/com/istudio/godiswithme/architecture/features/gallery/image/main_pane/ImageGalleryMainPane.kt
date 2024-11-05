package com.istudio.godiswithme.architecture.features.gallery.image.main_pane

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
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.domain_entity.GodData
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageGalleryMainPane(modifier: Modifier = Modifier, onClick: (GodData) -> Unit) {
    val viewModel: ImageGalleryMainPaneVm = koinViewModel()

    Scaffold(topBar = { TopAppBar(title = { Text(text = "God Images") }) }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 210.dp), // Set the minimum size for each cell
            modifier = Modifier.padding(it)
        ) {
            items(viewModel.state.value) { godData ->
                val bitmapImage = godData.godImage
                if (bitmapImage != null) {

                    viewModel.loadBitmap(bitmapImage)?.asImageBitmap()?.let { bitmp ->
                        Image(
                            painter = BitmapPainter(bitmp),
                            contentDescription = godData.godName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp)
                                .clickable { onClick.invoke(godData) }
                        )
                    }

                }
            }
        }
    }
}