package com.varanegar.warehousemanagement.data.room.entity.base

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseEntity {
    @PrimaryKey
    open var id: Long = 0
}