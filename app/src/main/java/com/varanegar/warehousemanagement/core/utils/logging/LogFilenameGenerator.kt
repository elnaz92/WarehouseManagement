package com.varanegar.warehousemanagement.core.utils.logging

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface FileNameGenerator {
    fun generateFileName(time: Long): String
}

object LogFilenameGenerator : FileNameGenerator {
    override fun generateFileName(time: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return "${formatter.format(Date(time))}.log"
    }
}
