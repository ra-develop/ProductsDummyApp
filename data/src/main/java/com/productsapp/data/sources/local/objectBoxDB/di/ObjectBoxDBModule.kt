package com.productsapp.data.sources.local.objectBoxDB.di

import com.productsapp.data.sources.local.objectBoxDB.repositories.GetAllHistoryImpl
import com.productsapp.data.sources.local.objectBoxDB.repositories.GetHistoryItemByCodeImpl
import com.productsapp.data.sources.local.objectBoxDB.repositories.PutHistoryItemImpl
import com.productsapp.domain.repositories.history.GetAllHistory
import com.productsapp.domain.repositories.history.GetHistoryItemByCode
import com.productsapp.domain.repositories.history.PutHistoryItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ObjectBoxDBModule {

    @Provides
    fun provideGetALlHistory() : GetAllHistory {
        return GetAllHistoryImpl()
    }
    @Provides
    fun provideGetHistoryItemByCode() : GetHistoryItemByCode {
        return GetHistoryItemByCodeImpl()
    }
    @Provides
    fun providePutHistoryItem() : PutHistoryItem {
        return PutHistoryItemImpl()
    }
}