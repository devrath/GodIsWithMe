package com.istudio.godiswithme.core.logger.applogger.local

import timber.log.Timber

internal class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Timber.tag(tag).d(msg)
    }

    override fun e(tag: String, msg: String, throwable: Throwable?) {
        Timber.tag(tag).e(throwable, msg)
    }

    override fun i(tag: String, msg: String) {
        Timber.tag(tag).i(msg)
    }

    override fun w(tag: String, msg: String) {
        Timber.tag(tag).w(msg)
    }
}