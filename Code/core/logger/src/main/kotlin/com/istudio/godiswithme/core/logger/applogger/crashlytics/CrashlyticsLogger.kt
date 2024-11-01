package com.istudio.godiswithme.core.logger.applogger.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.istudio.godiswithme.core.logger.applogger.remote.RemoteLogger
import javax.inject.Inject

class CrashlyticsLogger @Inject constructor(
    private val firebaseCrashlytics: FirebaseCrashlytics
) : RemoteLogger {

    override fun log(priority: Int?, tag: String?, message: String?, throwable: Throwable?) {
        firebaseCrashlytics.log("${tag ?: ""}: ${message ?: ""}")
        throwable?.let { logException(it) }
    }

    override fun logException(throwable: Throwable) {
        firebaseCrashlytics.recordException(throwable)
    }
}
