package com.istudio.godiswithme.architecture.data.repository

import android.app.Activity
import com.istudio.godiswithme.architecture.data.services.language.LanguageHelperService
import com.istudio.godiswithme.architecture.domain.repository.LanguageRepository

class LanguageRepositoryImpl(
    private val languageHelperService: LanguageHelperService
) : LanguageRepository {

    override fun changeLanguage(languageCode: String, activity: Activity) =
        languageHelperService.changeLanguage(languageCode,activity)

    override fun getLanguageCode() = languageHelperService.getLanguageCode()

}