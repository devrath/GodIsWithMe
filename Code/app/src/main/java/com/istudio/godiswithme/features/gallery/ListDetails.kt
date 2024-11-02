package com.istudio.godiswithme.features.gallery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailsScaffold(modifier: Modifier = Modifier) {

    val navigator = rememberListDetailPaneScaffoldNavigator<Place>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective.copy(horizontalPartitionSpacerSize = 20.dp, defaultPanePreferredWidth = 100.dp),
        value = navigator.scaffoldValue,
        listPane = {
            ListScreen {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
            }
        },
        detailPane = {
            navigator.currentDestination?.content?.let {
                DetailsScreen(place = it)
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier, onClick: (Place) -> Unit) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = "My List") }) }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(placeLists) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable { onClick.invoke(it) })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier, place: Place) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = place.name) }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                model = place.image, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = place.description,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            )
        }
    }

}