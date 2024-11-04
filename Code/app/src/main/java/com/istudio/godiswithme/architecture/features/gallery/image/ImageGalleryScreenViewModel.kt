package com.istudio.godiswithme.architecture.features.gallery.image

import androidx.lifecycle.ViewModel
import com.istudio.godiswithme.navigation.GodImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImageGalleryScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow<List<GodImage>>(emptyList())
    val state = _state.asStateFlow()

    init {
        // Load initial data or handle any business logic
    }

    fun loadImages() {
        // Logic to load images
    }

}