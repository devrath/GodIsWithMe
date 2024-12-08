package com.istudio.godiswithme.architecture.domain.repository

import android.app.Activity
import android.content.Context

interface LanguageRepository {
    fun changeLanguage(languageCode: String, activity: Activity)
    fun getLanguageCode(): String
}