package com.istudio.godiswithme.architecture.data.repository

import com.istudio.godiswithme.architecture.data.services.language.LanguageHelperService
import com.istudio.godiswithme.architecture.domain.repository.LanguageRepository

class LanguageRepositoryImpl(
    private val languageHelperService: LanguageHelperService
) : LanguageRepository {

    override fun changeLanguage(languageCode: String) =
        languageHelperService.changeLanguage(languageCode)

    override fun getLanguageCode() = languageHelperService.getLanguageCode()

}