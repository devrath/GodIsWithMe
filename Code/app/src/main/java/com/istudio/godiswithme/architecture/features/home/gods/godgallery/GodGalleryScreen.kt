package com.istudio.godiswithme.architecture.features.home.gods.godgallery

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.istudio.godiswithme.R
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.SideEffect
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiState
import com.istudio.godiswithme.common.mvi.CollectSideEffect
import com.istudio.godiswithme.common.mvi.unpack
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun GodGalleryScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    val viewModel: GodGalleryVm = koinViewModel()
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
                        GodCard(bitmp,godData.godName){
                            onClick.invoke(godData.godName)
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun GodCard(
    item: ImageBitmap, godName: String, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            Image(
                bitmap = item,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = godName,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}