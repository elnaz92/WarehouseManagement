package com.varanegar.warehousemanagement.core.utils.serializers

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

class AppSerializer<T>(
    override val defaultValue: T,
    private val serializer: KSerializer<T>
) : Serializer<T> {

    override suspend fun readFrom(input: InputStream): T {
        return try {
            val content = withContext(Dispatchers.IO) {
                input.readBytes().decodeToString()
            }
            if (content.isEmpty()) return defaultValue

            AppJson.decodeFromString(serializer, content)
        } catch (e: Exception) {
            Timber.e(e)
            defaultValue
        }
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        withContext(Dispatchers.IO) {
            val jsonString = AppJson.encodeToString(serializer, t)
            output.write(jsonString.encodeToByteArray())
            output.flush()
        }
    }
}