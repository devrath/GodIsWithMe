package com.istudio.godiswithme.architecture.features.gallery.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.navigation.GodImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageGalleryScreenViewModel(
    private val getGodsListUseCase: GetGodsListUseCase,
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

    fun loadImages() {
        // Logic to load images
    }

}