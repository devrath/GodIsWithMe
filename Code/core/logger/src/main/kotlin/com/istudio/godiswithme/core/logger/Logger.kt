package com.istudio.godiswithme.core.logger

interface Logger {
    fun d(tag: String, msg: String)
    fun e(tag: String, msg: String, throwable: Throwable? = null)
    fun i(tag: String, msg: String)
    fun w(tag: String, msg: String)
}