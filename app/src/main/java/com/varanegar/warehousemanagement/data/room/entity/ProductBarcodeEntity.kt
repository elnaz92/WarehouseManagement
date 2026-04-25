package com.varanegar.warehousemanagement.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import com.varanegar.warehousemanagement.core.utils.serializers.BigDecimalSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Entity(tableName = "ProductBarcode", indices = [Index("productId")])
@Serializable
data class ProductBarcodeEntity(
    @SerialName("goodsRef")
    val productId: Long,
    @SerialName("barCode")
    val barcode: String? = null,
    val isDefault: Boolean,
    @SerialName("goodsBarcodeQty")
    @Contextual
    val productBarcodeQty: BigDecimal?,
    @SerialName("goodsBarcodeName")
    val productBarcodeName: String?,
    val isActive: Int,
) : BaseEntity()

//Response Sample:
//{
//    "id": 1,
//    "goodsRef": 1,
//    "barCode": "2222",
//    "isDefault": true,
//    "goodsBarcodeQty": 2,
//    "goodsBarcodeName": "ککک",
//    "isActive": 0
//},
