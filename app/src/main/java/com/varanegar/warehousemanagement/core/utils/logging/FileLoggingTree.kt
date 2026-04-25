package com.varanegar.warehousemanagement.core.utils.logging

import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import timber.log.Timber
import java.io.File
import java.text.DateFormat
import java.util.Date

/**
 * Created by Elnaz Hashemzadeh on 4/6/2026 A.
 */

class FileLoggingTree(context: Context) : Timber.DebugTree() {
    private val printer: FilePrinter
    private val packageName: String = context.packageName
    private var versionName = "unknown"

    init {
        val logDirectory = getLogDirectory(context)
        printer = FilePrinter(logDirectory, LogFilenameGenerator)
        try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            versionName = packageInfo.versionName ?: "unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun createStackElementTag(element: StackTraceElement): String {
        return "[$packageName/$versionName/${super.createStackElementTag(element)}:${element.lineNumber}]"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val currentDateTime = DateFormat.getDateTimeInstance().format(Date())
        val formattedMessage = "[Time: $currentDateTime] $message"
        printer.println(priority, tag, formattedMessage)
    }

    companion object {
        private fun getAbsoluteFile(relativePath: String, context: Context): File {
            val state = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == state) {
                File(context.getExternalFilesDir(null), relativePath)
            } else {
                File(context.filesDir, relativePath)
            }
        }

        @JvmStatic
        fun getLogDirectory(context: Context): String {
            return getAbsoluteFile("logs", context).absolutePath
        }
    }
}