package com.varanegar.warehousemanagement.data.room.entity

import androidx.room.Entity
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "AccYear")
data class AccYearEntity(
    val accYear: Int,
    val title: String
) : BaseEntity()

//{
//    "id": 1,
//    "accYear": 1400,
//    "title": "1400",
//    "startDate": "1400/01/01",
//    "endDate": "1400/12/29"
//},