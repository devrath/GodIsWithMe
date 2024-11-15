package com.istudio.godiswithme.architecture.data.services.language

import android.content.Context

interface LanguageHelperService {
    fun changeLanguage(languageCode: String)
    fun getLanguageCode(): String
}