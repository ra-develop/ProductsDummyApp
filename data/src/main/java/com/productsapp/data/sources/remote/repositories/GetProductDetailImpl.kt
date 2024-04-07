package com.productsapp.data.sources.remote.repositories

import com.productsapp.data.sources.remote.api.ApiService
import com.productsapp.data.sources.remote.models.mappers.dummyJson.toDomain
import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.domain.repositories.product.GetProductDetail
import javax.inject.Inject

class GetProductDetailImpl @Inject constructor(private val apiService: ApiService) : GetProductDetail {
    override suspend fun getDetail(productCode: String): Product {
        try {
            return apiService.getDetail(productCode).toDomain()
        } catch (e: Exception) {
            throw e
        }
    }
}