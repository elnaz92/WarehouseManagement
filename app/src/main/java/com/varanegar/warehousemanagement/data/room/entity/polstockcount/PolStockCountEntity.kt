package com.varanegar.warehousemanagement.data.room.entity.polstockcount

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.varanegar.warehousemanagement.core.enums.CountMethodTypeEnum
import com.varanegar.warehousemanagement.data.room.entity.StockEntity
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Elnaz Hashemzadeh on 4/15/2026 A.
 */

@Serializable
@Entity(tableName = "PolStockCount",
    indices = [
        Index("stockId")
    ],
    foreignKeys = [ForeignKey(
        entity = StockEntity::class,
        parentColumns = ["id"],
        childColumns = ["stockId"],
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class PolStockCountEntity(
    @SerialName("vocherDate")
    val stockCountDate: String,
    @SerialName("tVocherNo")
    val stockCountNumber: String,
    @SerialName("stockDCRef")
    val stockId: Long,
    val countType: CountMethodTypeEnum?
) : BaseEntity()