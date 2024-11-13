@file:OptIn(ExperimentalMaterial3Api::class)

package com.istudio.godiswithme.architecture.features.home.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.SideEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.R
import com.istudio.godiswithme.architecture.features.home.settings.composables.SettingsRow
import com.istudio.godiswithme.architecture.features.home.settings.composables.languageselection.LanguageSelectionBottomSheet
import com.istudio.godiswithme.architecture.features.home.settings.composables.languageselection.LanguageSelectionContent
import com.istudio.godiswithme.common.mvi.CollectSideEffect
import com.istudio.godiswithme.common.mvi.unpack
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import com.istudio.godiswithme.ux.designsystem.preview.WindowSizeClassPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    languageSelected: (AppLanguage) -> Unit
) {
    val viewModel: SettingsScreenVm = koinViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CurrentScreen(uiState, sideEffect, onAction, languageSelected)
}

@Composable
private fun CurrentScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
    languageSelected: (AppLanguage) -> Unit,
) {
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when (it) {
            SideEffect.DisplayError -> {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            is SideEffect.LanguageUpdated -> languageSelected(it.language)
        }
    }

    val sheetState = rememberModalBottomSheetState()
    val languageCoroutineScope = rememberCoroutineScope()

    SettingsScreenContent(uiState, onAction)

    LanguageList(sheetState, uiState, onAction)

}

@Composable
private fun SettingsScreenContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val language = stringResource(R.string.str_language)
        val rowImage = Icons.Default.Language
        SettingsRow(
            rowImage = rowImage,
            rowName = language,
            selectedLanguage = uiState.selectedLanguage.displayName,
        ) {
            onAction(UiAction.LanguageSelectionDismissed(isDisplayed = true))
        }

    }
}

@Composable
private fun LanguageList(
    sheetState: SheetState,
    uiState: UiState,
    onAction: (UiAction) -> Unit
) {
    LanguageSelectionBottomSheet(
        sheetState = sheetState,
        isLanguageSelectionDisplayed = uiState.isLanguageSelectionDisplayed,
        onDismissRequest = {
            onAction(UiAction.LanguageSelectionDismissed(isDisplayed = false))
        }
    ) {
        // list of sheet languages
        LanguageSelectionContent(
            languages = uiState.languages,
            languageSelectionClick = { selectedLanguage ->
                onAction(UiAction.LanguageSelectionDismissed(isDisplayed = false))
                onAction(UiAction.UserUpdatedLanguage(language = selectedLanguage))
            }
        )
    }
}


@WindowSizeClassPreviews
@Composable
private fun MainScreenPreview() {
    GodIsWithMeTheme {
        /* CurrentScreen(
             uiState = UiState(),
             sideEffect = emptyFlow(),
             onAction = {}
         )*/
    }
}
