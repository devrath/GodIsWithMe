package com.istudio.godiswithme.architecture.features.home.settings

import android.widget.Toast
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
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiState
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.UiAction
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenContract.SideEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.R
import com.istudio.godiswithme.architecture.features.home.settings.composables.SettingsRow
import com.istudio.godiswithme.common.mvi.CollectSideEffect
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
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when (it) {
            SideEffect.DisplayError -> {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

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
                onAction(UiAction.OnLanguageChangeClick)
            }
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
