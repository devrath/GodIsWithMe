@file:OptIn(ExperimentalMaterial3Api::class)

package com.istudio.godiswithme.architecture.features.home.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.SideEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
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
    val context = LocalContext.current
    val languageCoroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    CollectSideEffect(sideEffect) {
        when (it) {
            SideEffect.DisplayError -> {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { LanguageSelectionContent() },
        sheetPeekHeight = 0.dp
    ) {

        // ---------> Entire screen content --------->
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
                    selectedLanguage = uiState.language.displayName,
                ){
                    languageCoroutineScope.launch {
                        onAction(UiAction.UpdateLanguageSelectionState(isDisplayed = true))
                        sheetState.show()
                    }
                }
            }
        }
        // ---------> Entire screen content --------->

        LanguageSelectionBottomSheet(
            sheetState = sheetState,
            isLanguageSelectionDisplayed = uiState.isLanguageSelectionDisplayed,
            onDismissRequest = {
                languageCoroutineScope.launch {
                    onAction(UiAction.UpdateLanguageSelectionState(isDisplayed = false))
                    sheetState.hide()
                }
            }
        ) {
            LanguageSelectionContent()
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
