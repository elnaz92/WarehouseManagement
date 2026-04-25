package com.varanegar.warehousemanagement.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import com.varanegar.warehousemanagement.core.utils.serializers.BigDecimalSerializer
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal


@Entity(tableName = "ProductUnit", indices = [Index("productId")])
@Serializable
data class ProductUnitEntity(
    @SerialName("goodsRef")
    val productId: Long,
    @SerialName("goodsCode")
    val productCode: String,
    @SerialName("goodsName")
    val productName: String?,
    @SerialName("unitRef")
    val unitId: Long,
    val unitName: String,
    @SerialName("qty")
    @Contextual
    val convertFactor: BigDecimal?,
    @SerialName("forInv")
    val forInventory: Boolean,
    val defaultForInventory: Boolean,
) : BaseEntity()

//{
//    "id": 1,
//    "goodsRef": 1,
//    "goodsCode": "1001",
//    "goodsName": "شکلات دارک جعبه چرم مسي بزرگ EX500 برنوتي بسته 10 گرمي 6. عددي",
//    "cartonType": 6.000,
//    "goodsUnitRef": 1,
//    "goodsPackUnitRef": 0,
//    "unitRef": 0,
//    "unitName": "کارتن",
//    "qty": 6.000,
//    "forSale": true,
//    "forRetSale": true,
//    "forInventory": true,
//    "defaultForSale": true,
//    "defaultForInventory": true,
//    "defaultForRetSale": true
//}

// توی فله ای دو واحد واحد کوچیکه میشه فله اش و واحد بزرگه میشه واحد بسته بندیش