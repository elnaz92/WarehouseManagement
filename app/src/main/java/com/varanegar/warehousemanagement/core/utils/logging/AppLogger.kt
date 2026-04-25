package com.varanegar.warehousemanagement.core.utils.logging

import timber.log.Timber

object AppLogger {

    fun v(message: String, vararg args: Any?) {
        Timber.Forest.v(message, *args)
    }

    fun d(message: String, vararg args: Any?) {
        Timber.Forest.d(message, *args)
    }

    fun i(message: String, vararg args: Any?) {
        Timber.Forest.i(message, *args)
    }

    fun w(message: String, vararg args: Any?) {
        Timber.Forest.w(message, *args)
    }

    fun w(t: Throwable?, message: String, vararg args: Any?) {
        Timber.Forest.w(t, message, *args)
    }

    fun e(message: String, vararg args: Any?) {
        Timber.Forest.e(message, *args)
    }

    fun e(t: Throwable?, message: String, vararg args: Any?) {
        Timber.Forest.e(t, message, *args)
    }

    fun tag(tag: String): Timber.Tree {
        return Timber.Forest.tag(tag)
    }
}