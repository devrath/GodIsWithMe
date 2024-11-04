package com.istudio.godiswithme.common.managers

import android.content.Context
import android.content.res.AssetManager

class AssetManager(private val context: Context) {
    fun provide(): AssetManager? {
        return context.assets
    }
}