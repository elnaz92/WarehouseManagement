package com.varanegar.warehousemanagement.data.room.base

import androidx.room.TypeConverter
import com.varanegar.warehousemanagement.core.enums.CountMethodTypeEnum
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromUUIDToString(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromStringToUUID(string: String?): UUID? {
        return string?.let { UUID.fromString(it) }
    }

    @TypeConverter
    fun fromBigDecimalToString(bigDecimal: BigDecimal?): String? {
        return bigDecimal?.toString()
    }

    @TypeConverter
    fun fromStringToBigDecimal(string: String?): BigDecimal? {
        return string?.toBigDecimal()
    }

    @TypeConverter
    fun fromCountMethodTypeEnumToInt(countMethodTypeEnum: CountMethodTypeEnum?): Int? {
        return countMethodTypeEnum?.countMethodType
    }

    @TypeConverter
    fun fromIntToCountMethodTypeEnum(value: Int?): CountMethodTypeEnum? {
        return value?.let { CountMethodTypeEnum.fromValue(it) }
    }
}
