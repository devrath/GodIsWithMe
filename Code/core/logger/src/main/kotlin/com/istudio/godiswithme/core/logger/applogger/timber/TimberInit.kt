package com.istudio.godiswithme.core.logger.applogger.timber

import com.google.android.datatransport.BuildConfig
import timber.log.Timber

class TimberInit(
    private val timberDebugTree : TimberDebugTree,
    private val timberReleaseTree : TimberReleaseTree
) {
    fun bootUp() {
        Timber.plant(
            when {
                BuildConfig.DEBUG -> timberDebugTree
                else -> timberReleaseTree
            }
        )
    }
}