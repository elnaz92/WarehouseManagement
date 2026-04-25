package com.varanegar.warehousemanagement.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LastUpdateProto(
    val stockCountLastUpdate: Long = 0L,
    val productBarcodeLastUpdate: Long = 0L,
    val voucherHeaderLastUpdate: Long = 0L
)