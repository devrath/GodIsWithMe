package com.istudio.godiswithme.architecture.data.services.language

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class LanguageHelperServiceImpl(
    private val context: Context
): LanguageHelperService {

    override fun changeLanguage(languageCode: String) {
        //if needed we can set in pref.

        //version >= 13
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            //version < 13
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    override fun getLanguageCode(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        } else {
            //version < 13
            AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        }
    }

}