package com.varanegar.warehousemanagement.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProto(
    val userName: String = "",
    val loginDate: Long = 0L
)