package com.istudio.godiswithme.architecture.features.gallery.image.main_pane

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.application.MY_APPLICATON_LOGS
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageGalleryMainPaneVm(
    private val getGodsListUseCase: GetGodsListUseCase,
    private val assetManager: AssetManager,
    private val logger: Logger
) : ViewModel() {

    private val _state = MutableStateFlow<List<GodData>>(emptyList())
    val state = _state.asStateFlow()

    init {
        // Load initial data or handle any business logic
        viewModelScope.launch {
            getGodsListUseCase.invoke().collect { godsList ->
                // Handle the list of gods here
                logger.d("result",godsList.toString())
                _state.value = godsList
            }
        }
    }

    fun loadBitmap(path: String): Bitmap? {
        return try {
            val inputStream = assetManager.provide()?.open(path)
            BitmapFactory.decodeStream(inputStream).also {
                inputStream?.close()
            }
        } catch (e: Exception) {
            logger.e(MY_APPLICATON_LOGS,e.message.orEmpty(),e)
            null
        }
    }

    fun loadImages() {
        // Logic to load images
    }

}