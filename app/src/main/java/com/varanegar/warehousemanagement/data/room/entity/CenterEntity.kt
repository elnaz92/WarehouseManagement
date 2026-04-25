package com.varanegar.warehousemanagement.data.room.entity

import androidx.room.Entity
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Center")
data class CenterEntity(
    val dcCode: Long,
    val dcName: String
) : BaseEntity()

// response sample:
//{
//    "id": 0,
//    "dcCode": "0",
//    "dcName": "ستاد مرکز",
//    "dcCategory": 0,
//    "managerName": null,
//    "operator1": null,
//    "address": null,
//    "operator2": null,
//    "phone": null,
//    "postCode": null,
//    "status": 1,
//    "faxNo": null,
//    "dlCode": null,
//    "areaRef": null,
//    "fifthCode": null,
//    "sixthCode": null,
//    "modifiedDateBeforeSend": null,
//    "userRefBeforeSend": null,
//    "safeDLCode": null,
//    "dcguid": "553EFDF0-7C00-4705-9557-E3EE69D5B3B9",
//    "isCentralize": false
//}