package com.istudio.godiswithme.architecture.data.services.language

sealed class AppLanguage(val code: String?, val displayName: String) {
    data object English : AppLanguage("en", "English")
    data object Hindi : AppLanguage("hi", "Hindi")
    data object Kannada : AppLanguage("kn", "Kannada")

    companion object {
        // Utility function to get a Language object from a code
        fun fromCode(code: String): AppLanguage {
            return when (code) {
                English.code -> English
                Hindi.code -> Hindi
                Kannada.code -> Kannada
                else -> English // Default to English
            }
        }

        // Utility function to get all languages
        fun getAllLanguages(): List<AppLanguage> = listOf(English, Hindi, Kannada)
    }
}