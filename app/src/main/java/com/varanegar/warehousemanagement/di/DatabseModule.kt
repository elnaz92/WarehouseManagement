package com.varanegar.warehousemanagement.di

import android.content.Context
import androidx.room.Room
import com.varanegar.warehousemanagement.data.room.base.PdaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun providePdaDatabase(@ApplicationContext context: Context) : PdaDatabase = PdaDatabase.buildDatabase(context)
}