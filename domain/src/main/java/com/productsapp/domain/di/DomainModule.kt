package com.productsapp.domain.di

import com.productsapp.domain.repositories.history.GetAllHistory
import com.productsapp.domain.repositories.history.GetHistoryItemByCode
import com.productsapp.domain.repositories.history.PutHistoryItem
import com.productsapp.domain.repositories.product.GetProductDetail
import com.productsapp.domain.repositories.product.GetProductsByPage
import com.productsapp.domain.usecases.history.GetAllHistoryUseCase
import com.productsapp.domain.usecases.history.GetHistoryByCodeUseCase
import com.productsapp.domain.usecases.history.PutHistoryUseCase
import com.productsapp.domain.usecases.product.GetProductDetailUseCase
import com.productsapp.domain.usecases.product.GetProductsByPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providerGetItemsByPageUseCase(repository: GetProductsByPage): GetProductsByPageUseCase {
        return GetProductsByPageUseCase(repository = repository)
    }

    @Provides
    fun providerGetDetailOfItemUseCase(repository: GetProductDetail): GetProductDetailUseCase {
        return GetProductDetailUseCase(repository = repository)
    }

    @Provides
    fun providerGetHistoryItemByCodeUseCase(repository: GetHistoryItemByCode): GetHistoryByCodeUseCase {
        return GetHistoryByCodeUseCase(repository = repository)
    }

    @Provides
    fun providerGetAllHistoryUseCase(repository: GetAllHistory): GetAllHistoryUseCase {
        return GetAllHistoryUseCase(repository = repository)
    }
    @Provides
    fun providerPutHistoryItemUseCase(repository: PutHistoryItem): PutHistoryUseCase {
        return PutHistoryUseCase(repository = repository)
    }

}