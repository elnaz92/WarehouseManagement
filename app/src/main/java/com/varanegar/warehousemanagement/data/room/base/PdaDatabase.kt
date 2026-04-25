package com.varanegar.warehousemanagement.data.room.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varanegar.warehousemanagement.data.room.entity.AccYearEntity
import com.varanegar.warehousemanagement.data.room.entity.CenterEntity
import com.varanegar.warehousemanagement.data.room.entity.ProductBarcodeEntity
import com.varanegar.warehousemanagement.data.room.entity.ProductUnitEntity
import com.varanegar.warehousemanagement.data.room.entity.StockEntity
import com.varanegar.warehousemanagement.data.room.entity.polstockcount.PolStockCountEntity
import com.varanegar.warehousemanagement.data.room.entity.voucher.SupplierEntity
import com.varanegar.warehousemanagement.data.room.entity.voucher.VoucherTypeEntity


@Database(entities = [AccYearEntity::class, CenterEntity::class, ProductBarcodeEntity::class, ProductUnitEntity::class, StockEntity::class, SupplierEntity::class, VoucherTypeEntity::class, PolStockCountEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PdaDatabase : RoomDatabase() {
//    abstract fun stockCountDao(): StockCountDao

    companion object {
        const val DATABASE_VERSION = 1
        fun buildDatabase(context: Context): PdaDatabase {
            return Room.databaseBuilder(
                context,
                PdaDatabase::class.java,
                "pda"
            ).build()
        }
    }
}