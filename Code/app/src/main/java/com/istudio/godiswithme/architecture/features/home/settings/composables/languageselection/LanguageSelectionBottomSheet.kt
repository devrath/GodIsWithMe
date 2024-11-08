@file:OptIn(ExperimentalMaterial3Api::class)

package com.istudio.godiswithme.architecture.features.home.settings.composables.languageselection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable

@Composable
fun LanguageSelectionBottomSheet(
    sheetState: SheetState,
    isLanguageSelectionDisplayed: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isLanguageSelectionDisplayed) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest
        ) {
            content()
        }
    }
}
