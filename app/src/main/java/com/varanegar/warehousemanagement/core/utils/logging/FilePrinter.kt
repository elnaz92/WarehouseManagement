package com.varanegar.warehousemanagement.core.utils.logging

import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.concurrent.Executors


class FilePrinter(
    private val folderPath: String,
    private val fileNameGenerator: FileNameGenerator
) {
    private val executor = Executors.newSingleThreadExecutor()
    private val writer = Writer()
    private var lastFileName: String? = null

    init {
        checkLogFolder()
        lastFileName = getLastFileName()
    }

    private fun getLastFileName(): String? {
        val dir = File(folderPath)
        val files = dir.list()
        if (files.isNullOrEmpty()) return null
        files.sort()
        return files.last()
    }

    /**
     * Make sure the folder of log file exists.
     */
    private fun checkLogFolder() {
        val folder = File(folderPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }
    }

    fun println(logLevel: Int, tag: String?, msg: String) {
        executor.execute {
            doPrintln(logLevel, tag, msg)
        }
    }

    /**
     * Do the real job of writing log to file.
     */
    private fun doPrintln(logLevel: Int, tag: String?, msg: String) {
        val newFileName = fileNameGenerator.generateFileName(System.currentTimeMillis())
        if (lastFileName == null) {
            require(newFileName.isNotBlank()) { "File name should not be empty." }
            lastFileName = newFileName
        }
        if (newFileName == lastFileName) {
            if (!writer.isOpened() || !writer.exists(newFileName)) {
                writer.open(newFileName)
            }
        } else {
            if (writer.isOpened()) {
                writer.close()
            }
            writer.open(newFileName)
            lastFileName = newFileName
        }

        val flattenedLog = flattenLog(logLevel, tag ?: "NO_TAG", msg)
        writer.appendLog(flattenedLog)
    }

    private fun flattenLog(logLevel: Int, tag: String, msg: String): String {
        val levelChar = when (logLevel) {
            Log.ASSERT -> "A/"
            Log.DEBUG -> "D/"
            Log.ERROR -> "E/"
            Log.INFO -> "I/"
            Log.VERBOSE -> "V/"
            Log.WARN -> "W/"
            else -> ""
        }
        return "$levelChar$tag : $msg"
    }

    /**
     * Used to write the flattened logs to the log file.
     */
    private inner class Writer {
        /**
         * Get the current log file.
         *
         * @return the current log file, maybe null
         */
        /**
         * The current log file.
         */
        private var file: File? = null

        private var bufferedWriter: BufferedWriter? = null

        /**
         * Whether the log file is opened.
         *
         * @return true if opened, false otherwise
         */
        fun isOpened(): Boolean = bufferedWriter != null


        /**
         * Open the file of specific name to be written into.
         *
         * @param newFileName the specific file name
         * @return true if opened successfully, false otherwise
         */
        fun open(newFileName: String): Boolean {
            file = File(folderPath, newFileName)

            // Create log file if not exists.
            if (file?.exists() == false) {
                try {
                    val parent = file?.parentFile
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs()
                    }
                    file?.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                    file = null
                    return false
                }
            }

            // Create buffered writer.
            return try {
                bufferedWriter = BufferedWriter(FileWriter(file, true))
                true
            } catch (e: Exception) {
                e.printStackTrace()
                file = null
                false
            }
        }

        fun exists(newFileName: String): Boolean {
            return File(folderPath, newFileName).exists()
        }

        /**
         * Close the current log file if it is opened.
         *
         * @return true if closed successfully, false otherwise
         */
        fun close(): Boolean {
            return try {
                bufferedWriter?.close()
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            } finally {
                bufferedWriter = null
                file = null
            }
        }

        /**
         * Append the flattened log to the end of current opened log file.
         *
         * @param flattenedLog the flattened log
         */
        fun appendLog(flattenedLog: String) {
            try {
                bufferedWriter?.apply {
                    write(flattenedLog)
                    newLine()
                    flush()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}