package com.istudio.godiswithme.architecture.features.home.gods

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import com.istudio.godiswithme.architecture.features.home.gods.goddetails.GodDetailsScreen
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreen
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreen
import com.istudio.godiswithme.architecture.features.home.gods.godSongs.GodSongsScreen
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ImageGalleryScreen(
    invokeAudioService : () -> Unit
) {
    val navigator = rememberSupportingPaneScaffoldNavigator<GodDestination>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            GodGalleryScreen { newGodName ->
                navigator.navigateTo(ThreePaneScaffoldRole.Secondary, GodDestination.GodDetails(newGodName))
            }
        },
        supportingPane = {
            navigator.currentDestination?.content?.let { godDestination ->
                GodScreen(
                    godName = godDestination.godName,
                    descriptionOnClick = {
                        navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, GodDestination.GodDetails(godDestination.godName))
                    },
                    songsOnClick = {
                        navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, GodDestination.GodDetailsSongs(godDestination.godName))
                    }
                )
            }
        },
        extraPane = {
            navigator.currentDestination?.content?.let { godDestination ->
                when (godDestination) {
                    is GodDestination.GodDetails -> GodDetailsScreen(godName = godDestination.godName)
                    is GodDestination.GodDetailsSongs -> GodSongsScreen(
                        godName = godDestination.godName,
                        invokeAudioService = invokeAudioService
                    )
                }
            }
        }
    )
}

@Parcelize
private sealed class GodDestination(val godName: String) : Parcelable {
    @Parcelize
    data class GodDetails(val name: String) : GodDestination(name)
    @Parcelize
    data class GodDetailsSongs(val name: String) : GodDestination(name)
}
