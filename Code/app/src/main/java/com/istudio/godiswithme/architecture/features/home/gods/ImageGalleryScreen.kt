package com.istudio.godiswithme.architecture.features.home.gods

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import com.istudio.godiswithme.architecture.features.home.gods.goddetails.GodDetailsScreen
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreen
import com.istudio.godiswithme.architecture.features.home.gods.god.ImageGallerySupportingPane

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ImageGalleryScreen() {
    val navigator = rememberSupportingPaneScaffoldNavigator<String>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            GodGalleryScreen { newGodName ->
                // Update the `supportingPane` only if the name changes
                navigator.navigateTo(ThreePaneScaffoldRole.Secondary, newGodName)
            }
        },
        supportingPane = {

            navigator.currentDestination?.content?.let { it ->
                ImageGallerySupportingPane(godName = it) {
                    navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, it)
                }
            }
        },
        extraPane = {
            navigator.currentDestination?.content?.let { godName ->
                GodDetailsScreen(godName = godName)
            }
        }
    )
}
