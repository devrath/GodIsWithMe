package com.istudio.godiswithme.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import com.istudio.godiswithme.features.gallery.audio.AudioGalleryScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var selectedItemIndex by remember { mutableIntStateOf(0) }
        val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
        NavigationSuiteScaffold(
            modifier = Modifier.padding(innerPadding),
            navigationSuiteItems = {
                Screen.entries.forEachIndexed { index, screen ->
                    item(
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title
                            )
                        },
                        label = {
                            Text(text = screen.title)
                        }
                    )
                }
            },
            layoutType = if (windowWidthClass == WindowWidthSizeClass.EXPANDED) {
                NavigationSuiteType.NavigationDrawer
            } else {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                    currentWindowAdaptiveInfo()
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Screen.entries.forEachIndexed { index, screen ->
                    if (index == 0) {
                        AudioGalleryScreen(navController)
                    }else if (index == 1) {
                        AudioGalleryScreen(navController)
                    }
                }
            }
        }
    }
}

enum class Screen(val title: String, val icon: ImageVector) {
    IMAGE_GALLERY("Image Gallery", Icons.Default.Image),
    AUDIO_GALLERY("Audio Gallery", Icons.Default.MusicNote),
    SETTINGS("Settings", Icons.Default.Settings),
}