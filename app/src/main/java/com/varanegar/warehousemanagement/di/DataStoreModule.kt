package com.varanegar.warehousemanagement.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.varanegar.warehousemanagement.core.utils.serializers.AppSerializer
import com.varanegar.warehousemanagement.data.models.LastUpdateProto
import com.varanegar.warehousemanagement.data.models.SettingsProto
import com.varanegar.warehousemanagement.data.models.TokenProto
import com.varanegar.warehousemanagement.data.models.UserProto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    @Provides
    @Singleton
    fun provideSettings(@ApplicationContext appContext: Context): DataStore<SettingsProto> =
        DataStoreFactory.create(
            serializer = AppSerializer(SettingsProto(), SettingsProto.serializer()),
            produceFile = { appContext.dataStoreFile("settings.json") }
        )

    @Provides
    @Singleton
    fun provideToken(@ApplicationContext appContext: Context): DataStore<TokenProto> =
        DataStoreFactory.create(
            serializer = AppSerializer(TokenProto(), TokenProto.serializer()),
            produceFile = { appContext.dataStoreFile("token.json") }
        )

    @Provides
    @Singleton
    fun provideUser(@ApplicationContext appContext: Context): DataStore<UserProto> =
        DataStoreFactory.create(
            serializer = AppSerializer(UserProto(), UserProto.serializer()),
            produceFile = { appContext.dataStoreFile("user.json") }
        )

    @Provides
    @Singleton
    fun provideLastUpdate(@ApplicationContext appContext: Context): DataStore<LastUpdateProto> =
        DataStoreFactory.create(
            serializer = AppSerializer(LastUpdateProto(), LastUpdateProto.serializer()),
            produceFile = { appContext.dataStoreFile("lastUpdate.json") }
        )
}