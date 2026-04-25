package com.varanegar.warehousemanagement.data.backup

import java.io.File

interface BackupManager {
    suspend fun backup(): Boolean
    suspend fun restore(file: File): Boolean
    fun getList(): List<File>
    suspend fun createBackupInfo(): BackupInfo
    suspend fun getBackupInfo(zipFile: String?): BackupInfo?
    suspend fun clearOldBackups()
    fun createBackupFolder(): File?
}