package com.istudio.godiswithme.architecture.domain.repository

import android.content.Context

interface LanguageRepository {
    fun changeLanguage(languageCode: String)
    fun getLanguageCode(): String
}