package com.istudio.godiswithme.architecture.features.home.settings.composables.languageselection

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.godiswithme.architecture.features.home.settings.AppLanguage

@Composable
fun LanguageSelectionContent(
    modifier: Modifier = Modifier,
    languages : List<AppLanguage>,
    languageSelectionClick: (AppLanguage) -> Unit
) {
    Column {
        languages.forEachIndexed { index, language ->
            BottomSheetListItem(
                title = language.displayName,
                onItemClick = {
                    languageSelectionClick(languages[index])
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    LanguageSelectionContent(
        languages = emptyList()
    ){}
}

@Composable
fun BottomSheetListItem(title: String, onItemClick: () -> Unit) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp).fillMaxWidth().clickable { onItemClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun BottomSheetListItemPreview() {
    BottomSheetListItem(title = "Language Name", onItemClick = { })
}