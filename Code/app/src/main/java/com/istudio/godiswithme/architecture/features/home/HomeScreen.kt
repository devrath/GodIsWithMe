package com.istudio.godiswithme.architecture.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import com.istudio.godiswithme.R
import com.istudio.godiswithme.architecture.features.gallery.ListDetailsScaffold
import com.istudio.godiswithme.architecture.features.gallery.image.ImageGalleryScreen

@Composable
fun HomeScreen(navController: NavHostController) {

    val selected = rememberSaveable { mutableStateOf(HomeDest.IMAGE_GALLERY.name) }
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()

    val layoutType = when (windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
        WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationRail
        WindowWidthSizeClass.COMPACT -> NavigationSuiteType.NavigationBar
        else -> NavigationSuiteType.NavigationBar
    }

    NavigationSuiteScaffold(
        layoutType = layoutType,
        navigationSuiteItems = {
            HomeDest.entries.forEach {
                item(selected = selected.value == it.name,
                    icon = { Icon(imageVector = it.imageVector, contentDescription = null) },
                    label = { Text(text = stringResource(id = it.resId)) }, onClick = {
                        selected.value = it.name
                    })
            }
        })
    {
        when (selected.value) {
            HomeDest.IMAGE_GALLERY.name -> ImageGalleryScreen()
            HomeDest.AUDIO_GALLERY.name -> ListDetailsScaffold()
        }
    }
}

enum class HomeDest(val resId:Int, val imageVector: ImageVector) {
    IMAGE_GALLERY(R.string.images, imageVector = Icons.Default.Image),
    AUDIO_GALLERY(R.string.audio, imageVector = Icons.Default.MusicNote),
}

