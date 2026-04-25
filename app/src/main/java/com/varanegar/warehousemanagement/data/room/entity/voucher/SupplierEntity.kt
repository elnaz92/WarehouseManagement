package com.varanegar.warehousemanagement.data.room.entity.voucher

import androidx.room.Entity
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Supplier")
data class SupplierEntity(
    val supplierName: String,
    val supplierCode: String,
    @SerialName("active")
    val isActive: Int
) : BaseEntity()

// response sample:
//{
//    "id": 1,
//    "supplierCode": "1",
//    "supplierName": "برنوتي",
//    "margine": 0.0,
//    "active": 1,
//    "office": null,
//    "dlCode": "3023001",
//    "contactId": 8,
//    "modifiedDateBeforeSend": "2021-07-07T01:34:57.393",
//    "uniqueId": 0,
//    "econCode": null,
//    "nationalCode": null
//},