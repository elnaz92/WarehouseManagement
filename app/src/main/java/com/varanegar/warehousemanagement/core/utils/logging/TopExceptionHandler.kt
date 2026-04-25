package com.varanegar.warehousemanagement.core.utils.logging

/**
 * Created by Elnaz Hashemzadeh on 4/7/2026 A.
 */

class TopExceptionHandler : Thread.UncaughtExceptionHandler {
    private val defaultUEH: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(t: Thread, e: Throwable) {
        AppLogger.e(e, "FATAL EXCEPTION: Application crashed unexpectedly in thread ${t.name}! ")
        defaultUEH?.uncaughtException(t, e)
    }
}
