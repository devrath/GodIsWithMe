package com.istudio.godiswithme.core.logger.applogger.timber

import timber.log.Timber
import javax.inject.Inject

class TimberDebugTree @Inject constructor() : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)

        // Shipbook tool or crashlytics
    }
}
