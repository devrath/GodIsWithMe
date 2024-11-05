package com.istudio.godiswithme.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.istudio.godiswithme.navigation.SetupNavGraph
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import com.istudio.godiswithme.ux.designsystem.preview.WindowSizeClassPreviews
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val mainActivity: MainVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GodIsWithMeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@WindowSizeClassPreviews
@Composable
private fun MainScreenPreview() {
    GodIsWithMeTheme {
        MainScreen()
    }
}