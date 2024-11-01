package com.istudio.godiswithme.core.logger.applogger.timber

import android.util.Log
import com.istudio.godiswithme.core.logger.applogger.crashlytics.CrashlyticsLogger
import timber.log.Timber
import javax.inject.Inject

class TimberReleaseTree @Inject constructor(
    private val crashlyticsLogger: CrashlyticsLogger
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.WARN, Log.ERROR -> { // only log errors/warnings with a throwable
                t?.let { crashlyticsLogger.log(priority, tag, message, it) }
            }
            else -> {} // no-op
        }
    }
}
