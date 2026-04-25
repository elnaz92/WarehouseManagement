package com.varanegar.warehousemanagement.core.utils.logging

import timber.log.Timber
import java.text.DateFormat
import java.util.Date

class DebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        val superTag = super.createStackElementTag(element)
        return "$superTag:${element.lineNumber}"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val currentDateTime = DateFormat.getDateTimeInstance().format(Date())
        val messageWithTime = "[Time: $currentDateTime] $message"
        super.log(priority, tag, messageWithTime, t)
    }
}
