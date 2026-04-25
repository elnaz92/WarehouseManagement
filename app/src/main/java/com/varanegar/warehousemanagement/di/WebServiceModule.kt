//package com.varanegar.warehousemanagement.di
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import com.varanegar.warehousemanagement.data.models.SettingsProto
//import com.varanegar.warehousemanagement.data.models.TokenProto
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//import kotlin.jvm.java
//
//@InstallIn(SingletonComponent::class)
//@Module
//object WebServiceModule {
//
//    @Provides
//    @Singleton
//    fun provideUserApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<UserApi> =
//        WebApiImpl(context, settingsDataStore, tokenDataStore, UserApi::class.java)
//
//    @Provides
//    @Singleton
//    fun provideStockApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<StockApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        StockApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideSupplierApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<SupplierApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        SupplierApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideCenterApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<CenterApi> =
//        WebApiImpl(context, settingsDataStore, tokenDataStore, CenterApi::class.java)
//
//    @Provides
//    @Singleton
//    fun provideVoucherTypeApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<VoucherTypeApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        VoucherTypeApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideStockProductApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<StockProductApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        StockProductApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideBatchStockProductApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<BatchStockProductApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        BatchStockProductApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideStockCountApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<StockCountApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        StockCountApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideProductBarcodeApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<ProductBarcodeApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        ProductBarcodeApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideVoucherProductApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<VoucherProductApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        VoucherProductApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideVoucherProductUnitApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<VoucherProductUnitApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        VoucherProductUnitApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideSendVoucherApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<SendVoucherApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        SendVoucherApi::class.java
//    )
//
//
//    @Provides
//    @Singleton
//    fun provideAccYearApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<AccYearApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        AccYearApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun provideDeleteDbFileApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<DeleteDbFileApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        DeleteDbFileApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun providePolStockCountApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<PolStockCountApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        PolStockCountApi::class.java
//    )
//
//    @Provides
//    @Singleton
//    fun providePolStockCountItemApi(
//        @ApplicationContext context: Context,
//        settingsDataStore: DataStore<SettingsProto>,
//        tokenDataStore: DataStore<TokenProto>,
//    ): WebApi<PolStockCountItemApi> = WebApiImpl(
//        context,
//        settingsDataStore,
//        tokenDataStore,
//        PolStockCountItemApi::class.java
//    )
//}