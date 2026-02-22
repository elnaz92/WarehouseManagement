package com.varanegar.warehousemanagement.data.room.dao

import androidx.room.PrimaryKey

/**
 * Created by Elnaz Hashemzadeh on 2/17/2026 A.
 */

abstract class IdBaseEntity: BaseEntity() {
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0
}