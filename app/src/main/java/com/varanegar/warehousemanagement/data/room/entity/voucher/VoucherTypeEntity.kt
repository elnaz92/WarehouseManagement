package com.varanegar.warehousemanagement.data.room.entity.voucher

import androidx.room.Entity
import androidx.room.Index
import com.varanegar.warehousemanagement.data.room.entity.base.BaseEntity
import kotlinx.serialization.Serializable


@Entity(tableName = "VoucherType", indices = [Index(value = ["code"], unique = true)])
@Serializable
data class VoucherTypeEntity(
    val code: Int,
    val title: String,
    val isManual: String?
): BaseEntity()


//{
//    "id": 12,
//    "code": 12,
//    "title": "برگشت توزيع",
//    "isManual": "1"
//},
//{
//    "id": 13,
//    "code": 13,
//    "title": "برگشت توزيع حواله اي",
//    "isManual": "1"
//},
//{
//    "id": 15,
//    "code": 15,
//    "title": "انبار به انبار بدهکار",
//    "isManual": "1"
//},
//{
//    "id": 16,
//    "code": 16,
//    "title": "انبارک به انبار بدهکار",
//    "isManual": "1"
//},
//{
//    "id": 20,
//    "code": 20,
//    "title": "رسيد انبار",
//    "isManual": "1"
//},
//{
//    "id": 21,
//    "code": 21,
//    "title": "درخواست تامين کالا",
//    "isManual": "1"
//},
//{
//    "id": 25,
//    "code": 25,
//    "title": "تعديل مثبت",
//    "isManual": "1"
//},
//{
//    "id": 30,
//    "code": 30,
//    "title": "ورودي ساير",
//    "isManual": "1"
//},
//{
//    "id": 40,
//    "code": 40,
//    "title": "سالم",
//    "isManual": "1"
//},
//{
//    "id": 41,
//    "code": 41,
//    "title": "برگشت از رزرو",
//    "isManual": "1"
//},
//{
//    "id": 55,
//    "code": 55,
//    "title": "برگشت خريد",
//    "isManual": "1"
//},
//{
//    "id": 65,
//    "code": 65,
//    "title": "انبار به انبار بستانکار",
//    "isManual": "1"
//},
//{
//    "id": 66,
//    "code": 66,
//    "title": "انبارک به انبار بستانکار",
//    "isManual": "1"
//},
//{
//    "id": 70,
//    "code": 70,
//    "title": "تعديل منفي",
//    "isManual": "1"
//},
//{
//    "id": 75,
//    "code": 75,
//    "title": "خروجي ساير",
//    "isManual": "1"
//},
//{
//    "id": 76,
//    "code": 76,
//    "title": "رزرو",
//    "isManual": "1"
//},
//{
//    "id": 85,
//    "code": 85,
//    "title": "ضايعاتي",
//    "isManual": "1"
//},
//{
//    "id": 95,
//    "code": 95,
//    "title": "معدومي",
//    "isManual": "1"
//}
