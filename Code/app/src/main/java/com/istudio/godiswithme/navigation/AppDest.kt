package com.istudio.godiswithme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.istudio.godiswithme.R

enum class AppDest(val resId:Int,val imageVector: ImageVector) {
    AUDIO_GALLERY(R.string.home, imageVector = Icons.Default.Home),
    IMAGE_GALLERY(R.string.favorites, imageVector = Icons.Default.Favorite)
}