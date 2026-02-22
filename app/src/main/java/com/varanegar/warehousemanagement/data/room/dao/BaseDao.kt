package com.varanegar.warehousemanagement.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Elnaz Hashemzadeh on 2/17/2026 A.
 */

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(items: List<T>): List<Long>

    @Update
    suspend fun update(item: T)

    @Update
    suspend fun update(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(items: List<T>): List<Long>

    @Delete
    suspend fun delete(item: T)
}
