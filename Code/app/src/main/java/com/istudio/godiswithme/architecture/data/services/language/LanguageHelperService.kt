package com.istudio.godiswithme.architecture.data.services.language

import android.app.Activity
import android.content.Context

interface LanguageHelperService {
    fun changeLanguage(languageCode: String, activity: Activity)
    fun getLanguageCode(): String
}