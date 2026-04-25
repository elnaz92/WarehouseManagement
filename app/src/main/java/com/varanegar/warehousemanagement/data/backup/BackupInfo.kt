package com.varanegar.warehousemanagement.data.backup

import kotlinx.serialization.Serializable

@Serializable
data class BackupInfo(
    val date: String? = null,
    val packageName: String? = null,
    val userName: String? = null,
    val accYear: String? = null,
    val centerId: Long? = null,
    val centerName: String? = null,
    val stockId: Long? = null,
    val stockName: String? = null,
    val appVersionName: String? = null,
    val appVersionCode: Int? = null,
    val deviceModel: String? = null,
    val deviceId: String? = null,
    val deviceSdk: Int? = null,
    val deviceBrand: String? = null,
    val deviceManufacturer: String? = null,
    val databaseVersion: Int? = null,
)
