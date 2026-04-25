package com.varanegar.warehousemanagement.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SettingsProto(
    val ip: String = "",
    val accYear: Int = 0,
    val centerId: String = "",
    val stockId: Long = 0L,
    val stockCountCalculatorTabPosition: Int = 0
)