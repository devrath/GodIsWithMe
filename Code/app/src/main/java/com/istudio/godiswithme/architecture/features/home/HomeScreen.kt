package com.istudio.godiswithme.architecture.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import com.istudio.godiswithme.R
import com.istudio.godiswithme.architecture.features.home.settings.Settings
import com.istudio.godiswithme.architecture.features.home.gods.ImageGalleryScreen

@Composable
fun HomeScreen(navController: NavHostController) {

    val selected = rememberSaveable { mutableStateOf(HomeDest.GODS_GALLERY.name) }
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
                navigationSuiteItem(selected, it)
            }
        })
    {
        when (selected.value) {
            HomeDest.GODS_GALLERY.name -> ImageGalleryScreen()
            HomeDest.SETTINGS.name -> Settings()
        }
    }
}


private fun NavigationSuiteScope.navigationSuiteItem(
    selected: MutableState<String>,
    it: HomeDest
) {
    item(selected = selected.value == it.name,
        icon = { Icon(imageVector = it.imageVector, contentDescription = it.name) },
        label = { Text(text = stringResource(id = it.resId)) },
        onClick = { selected.value = it.name }
    )
}

private enum class HomeDest(val resId:Int, val imageVector: ImageVector) {
    GODS_GALLERY(R.string.gods, imageVector = Icons.Default.Image),
    SETTINGS(R.string.settings, imageVector = Icons.Default.Settings),
}

