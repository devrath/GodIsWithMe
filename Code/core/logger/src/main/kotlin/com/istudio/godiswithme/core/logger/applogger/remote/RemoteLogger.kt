package com.istudio.godiswithme.core.logger.applogger.remote

/**
 * Abstraction for components that send logs and exceptions to remote reporting tools.
 */
interface RemoteLogger {

    fun log(priority: Int?, tag: String?, message: String?, throwable: Throwable?)

    fun logException(throwable: Throwable)
}
