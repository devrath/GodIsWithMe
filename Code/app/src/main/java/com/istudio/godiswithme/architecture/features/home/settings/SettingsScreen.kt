package com.istudio.godiswithme.architecture.features.home.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryScreenContract.SideEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.R
import com.istudio.godiswithme.common.mvi.unpack
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import com.istudio.godiswithme.ux.designsystem.preview.WindowSizeClassPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val viewModel: SettingsScreenVm = koinViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CurrentScreen(uiState, sideEffect, onAction)
}

@Composable
private fun CurrentScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val language = stringResource(R.string.str_language)
            val rowImage = Icons.Default.Language
            SettingsRow(
                rowImage = rowImage,
                rowName = language,
                selectedLanguage = "Default",
            ){

            }
        }
    }

}

@Composable
private fun SettingsRow(
    modifier: Modifier = Modifier,
    selectedLanguage: String,
    rowImage: ImageVector,
    rowName: String,
    languageClickAction:() -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp).clickable(onClick = languageClickAction),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = rowImage, contentDescription = rowName)
            Spacer(modifier = Modifier.width(5.dp))
            Text(rowName)
        }
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(selectedLanguage)
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = selectedLanguage)
        }
    }
}

@WindowSizeClassPreviews
@Composable
private fun MainScreenPreview() {
    GodIsWithMeTheme {
        CurrentScreen(
            uiState = UiState(),
            sideEffect = emptyFlow(),
            onAction = {}
        )
    }
}
