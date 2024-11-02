package com.istudio.godiswithme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.istudio.godiswithme.R

enum class AppDest(val resId:Int,val imageVector: ImageVector) {
    HOME(R.string.home, imageVector = Icons.Default.Home),
    FAVORITE(R.string.favorites, imageVector = Icons.Default.Favorite)
}