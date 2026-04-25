package com.varanegar.warehousemanagement.core.enums


import kotlinx.serialization.Serializable

@Serializable
enum class CountMethodTypeEnum(val countMethodType: Int) {
    Complete(1) {
        override fun toString() = "کامل"
    },
    Partial(2) {
        override fun toString() = "قسمتی"
    };

    companion object {
        fun fromValue(value: Int): CountMethodTypeEnum? =
            entries.firstOrNull { it.countMethodType == value }
    }
}
