package com.varanegar.warehousemanagement.data.room.dao

import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Created by Elnaz Hashemzadeh on 2/17/2026 A.
 */

abstract class BaseEntityDao<T : BaseEntity> : BaseDao<T> {
    abstract fun getTableName(): String

    @RawQuery
    protected abstract suspend fun getAllQuery(query: SupportSQLiteQuery): List<T>

    suspend fun getAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()}")
        return getAllQuery(query)
    }

    @RawQuery
    protected abstract suspend fun getItemQuery(query: SupportSQLiteQuery): T?

    suspend fun getItem(id: Long): T? {
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()} WHERE id = $id")
        return getItemQuery(query)
    }

    @RawQuery
    protected abstract suspend fun deleteAllQuery(query: SupportSQLiteQuery): Int

    suspend fun deleteAll() {
        val query = SimpleSQLiteQuery("DELETE FROM ${getTableName()}")
        deleteAllQuery(query)
    }
}
