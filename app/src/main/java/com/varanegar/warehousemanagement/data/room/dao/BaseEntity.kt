package com.varanegar.warehousemanagement.data.room.dao

import androidx.room.PrimaryKey

/**
 * Created by Elnaz Hashemzadeh on 2/17/2026 A.
 */

abstract class BaseEntity {
    @PrimaryKey
    open var id: Long = 0
}
