package com.varanegar.warehousemanagement.data.room.entity

import androidx.room.Entity
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
@Entity(tableName = "Stock")
data class StockEntity(
    @SerialName("stockDCCode")
    val stockCode: String,
    @SerialName("stockDCName")
    val stockName: String,
    @SerialName("saleOfficeRef")
    val saleOfficeId: Long?
) : BaseEntity()


//{
//    "@odata.context": "https://vnb2b.ir/$metadata#stockdcmodel",
//    "value": [
//    {
//        "id": 24,
//        "stockDCCode": "102",
//        "stockDCName": "104",
//        "stockType": 15,
//        "stockType1": "بلي",
//        "stockType2": "بلي",
//        "stockType4": "بلي",
//        "stockType8": "بلي",
//        "stockType16": "خير",
//        "stockTypeFlag1": true,
//        "stockTypeFlag2": true,
//        "stockTypeFlag4": true,
//        "stockTypeFlag8": true,
//        "stockTypeFlag16": false,
//        "dcRef": 2,
//        "dcCode": "56",
//        "dcName": "رشت",
//        "saleOfficeRef": 1,
//        "saleOfficeName": "دفتر مرکزي",
//        "dcSaleOfficeRef": 9,
//        "dlCode": null,
//        "shipTypeRef": 2,
//        "shipTypeName": "يخچالي",
//        "stockDcRef": 24,
//        "soRef": 1,
//        "allowNegativeOnHandQty": false,
//        "allowNegativeCardexQty": false,
//        "doPricing": false
//    },
//    ...
//    ]
//}
