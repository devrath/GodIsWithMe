package com.istudio.godiswithme.architecture.features.gallery.image

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import com.istudio.godiswithme.architecture.features.gallery.image.extra_pane.ImageGalleryExtraPane
import com.istudio.godiswithme.architecture.features.gallery.image.main_pane.ImageGalleryMainPane
import com.istudio.godiswithme.architecture.features.gallery.image.supporting_pane.ImageGallerySupportingPane

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
            ImageGalleryMainPane {
                navigator.navigateTo(ThreePaneScaffoldRole.Secondary, it)
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
            navigator.currentDestination?.content?.let {
                ImageGalleryExtraPane(godName = it)
            }
        }
    )

}