package com.varanegar.warehousemanagement.core.utils.logging

import android.content.Context
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Executors

/**
 * Created by Elnaz Hashemzadeh on 4/7/2026 A.
 */

object LogConfig {

    fun init(context: Context) {
        val appContext = context.applicationContext
        Thread.setDefaultUncaughtExceptionHandler(TopExceptionHandler())
        if (Timber.treeCount == 0) {
            Timber.plant(DebugTree(), FileLoggingTree(appContext))
        }
        Executors.newSingleThreadExecutor().execute {
            wipeOldLogs(appContext)
        }
    }

    private fun wipeOldLogs(context: Context) {
        val path =
            FileLoggingTree.getLogDirectory(context)
        val directory = File(path)
        if (!directory.exists()) return
        val files = directory.listFiles { file -> file.name.endsWith(".log") } ?: return
        if (files.isEmpty()) return
        val cal = Calendar.getInstance().apply {
            add(Calendar.DATE, -10)
        }
        val tenDaysAgo = cal.time
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        files.forEach { file ->
            try {
                val dateString = file.name.removeSuffix(".log")
                val fileDate = format.parse(dateString)

                if (fileDate != null && fileDate.before(tenDaysAgo)) {
                    val isDeleted = file.delete()
                    if (isDeleted) {
                        AppLogger.d("Old log file deleted: ${file.name}")
                    }
                }
            } catch (e: Exception) {
                AppLogger.e(e, "Error processing or deleting log file: ${file.name}")
            }
        }
    }
}
