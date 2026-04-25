package com.varanegar.warehousemanagement.core.utils.serializers


import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.math.BigDecimal

val AppJson = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = true
    serializersModule = SerializersModule {
        contextual(BigDecimal::class, BigDecimalSerializer)
    }
}