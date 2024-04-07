package com.productsapp.data.sources.remote.di

import com.productsapp.data.sources.remote.api.ApiService
import com.productsapp.data.sources.remote.repositories.GetProductDetailImpl
import com.productsapp.data.sources.remote.repositories.GetProductsByPageImpl
import com.productsapp.domain.repositories.product.GetProductDetail
import com.productsapp.domain.repositories.product.GetProductsByPage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetProductsByPage(apiService: ApiService): GetProductsByPage {
        return GetProductsByPageImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetProductDetail(apiService: ApiService): GetProductDetail {
        return GetProductDetailImpl(apiService)
    }

}