package com.varanegar.warehousemanagement.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenProto(
    val accessToken: String = "",
    val expiresAt: String = ""
)