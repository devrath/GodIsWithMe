package com.istudio.godiswithme.architecture.features.home.gallery

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.istudio.godiswithme.architecture.features.home.gallery.extra_pane.ImageGalleryExtraPane
import com.istudio.godiswithme.architecture.features.home.gallery.main_pane.ImageGalleryMainPane
import com.istudio.godiswithme.architecture.features.home.gallery.supporting_pane.ImageGallerySupportingPane

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ImageGalleryScreen() {
    val navigator = rememberSupportingPaneScaffoldNavigator<String>()
    val currentSupportingPane = remember { mutableStateOf<String?>(null) }

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            ImageGalleryMainPane { newGodName ->
                // Update the `supportingPane` only if the name changes
                if (currentSupportingPane.value != newGodName) {
                    currentSupportingPane.value = newGodName
                    navigator.navigateTo(ThreePaneScaffoldRole.Secondary, newGodName)
                }
            }
        },
        supportingPane = {
            currentSupportingPane.value?.let { godName ->
                ImageGallerySupportingPane(godName = godName) {
                    navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, godName)
                }
            }
        },
        extraPane = {
            navigator.currentDestination?.content?.let { godName ->
                ImageGalleryExtraPane(godName = godName)
            }
        }
    )
}
