package com.istudio.godiswithme.architecture.features.home.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.R
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import com.istudio.godiswithme.ux.designsystem.preview.WindowSizeClassPreviews

@Composable
fun SettingsRow(
    modifier: Modifier = Modifier,
    selectedLanguage: String,
    rowImage: ImageVector,
    rowName: String,
    languageClickAction: () -> Unit
) {

    val rowPaddingTop = 10.dp
    val rowPaddingBottom = 10.dp
    val rowIconAndNameSpacing = 5.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = rowPaddingTop, bottom = rowPaddingBottom)
            .clickable(onClick = languageClickAction),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = rowImage,
                contentDescription = rowName
            )
            Spacer(modifier = Modifier.width(rowIconAndNameSpacing))
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = rowName
            )
        }
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = selectedLanguage
            )
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Default.ChevronRight,
                contentDescription = selectedLanguage
            )
        }
    }
}

@WindowSizeClassPreviews
@Composable
private fun LanguageRowPreview() {
    GodIsWithMeTheme {
        val language = stringResource(R.string.str_language)
        val rowImage = Icons.Default.Language
        SettingsRow(
            selectedLanguage = "English",
            rowName = language,
            rowImage = rowImage
        ) {

        }
    }
}
