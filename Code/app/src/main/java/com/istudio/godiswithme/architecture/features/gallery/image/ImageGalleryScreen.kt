package com.istudio.godiswithme.architecture.features.gallery.image

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.domain_entity.GodData
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ImageGalleryScreen() {
    val navigator = rememberSupportingPaneScaffoldNavigator<GodData>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            MainPane {
                navigator.navigateTo(ThreePaneScaffoldRole.Secondary, it)
            }
        },
        supportingPane = {
            navigator.currentDestination?.content?.let { it ->
                SupportingPane(godData = it) {
                    navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, it)
                }
            }
        },
        extraPane = {
            navigator.currentDestination?.content?.let {
                ExtraPane(godData = it)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPane(modifier: Modifier = Modifier, onClick: (GodData) -> Unit) {

    val viewModel: ImageGalleryScreenViewModel = koinViewModel()

    Scaffold(topBar = { TopAppBar(title = { Text(text = "God Images") }) }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 210.dp), // Set the minimum size for each cell
            modifier = Modifier.padding(it)
        ) {
            items(viewModel.state.value) { godData ->
                val bitmapImage = godData.godImage
                if (bitmapImage != null) {
                    Image(
                        painter = BitmapPainter(bitmapImage.asImageBitmap()),
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

@Composable
fun SupportingPane(
    modifier: Modifier = Modifier,
    godData: GodData,
    onClick: (GodData) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onClick.invoke(godData) }) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            val bitmapImage = godData.godImage
            if (bitmapImage != null) {
                Image(
                    painter = BitmapPainter(bitmapImage.asImageBitmap()),
                    contentDescription = "Click for more information",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraPane(modifier: Modifier = Modifier, godData: GodData) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Details") }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val bitmapImage = godData.godImage
            if (bitmapImage != null) {
                Image(
                    painter = BitmapPainter(bitmapImage.asImageBitmap()),
                    contentDescription = godData.godName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }


            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = godData.godName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = godData.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

        }
    }

}