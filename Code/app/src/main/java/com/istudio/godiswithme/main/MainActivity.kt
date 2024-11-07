package com.istudio.godiswithme.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.istudio.godiswithme.application.APP_TAG
import com.istudio.godiswithme.architecture.features.home.audio.AudioVm
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.core.player.service.JetAudioService
import com.istudio.godiswithme.navigation.SetupNavGraph
import com.istudio.godiswithme.ux.designsystem.GodIsWithMeTheme
import com.istudio.godiswithme.ux.designsystem.preview.WindowSizeClassPreviews
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainVm>()
    private val logger : Logger by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GodIsWithMeTheme {
                MainScreen(invokeAudioService = {
                    startService()
                })
            }
        }
        observeEvents()
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.events.collect { uiState ->
                    when (uiState) {
                        is MainUiState.StartPlayerService -> startService()
                    }
                }
            }
        }
    }

    private fun startService() = try {
        if (!mainViewModel.isAudioServiceRunning) {
            val intent = Intent(this, JetAudioService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            logger.d(APP_TAG,"Audio service is initiated")
            mainViewModel.isAudioServiceRunning = true
        } else {
            logger.d(APP_TAG,"Audio service is already running")
        }
    }catch (ex: Exception){
        logger.d(APP_TAG,"Exception occurred while starting the service")
    }
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    invokeAudioService : () -> Unit
) {
    val navController = rememberNavController()
    SetupNavGraph(
        navController = navController,
        invokeAudioService = invokeAudioService
    )
}

@WindowSizeClassPreviews
@Composable
private fun MainScreenPreview() {
    GodIsWithMeTheme {
        MainScreen(invokeAudioService = {})
    }
}