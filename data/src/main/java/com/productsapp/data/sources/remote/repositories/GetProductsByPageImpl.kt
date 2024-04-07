package com.productsapp.data.sources.remote.repositories

import com.productsapp.data.sources.remote.api.ApiService
import com.productsapp.data.sources.remote.models.mappers.dummyJson.toDomain
import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.domain.repositories.product.GetProductsByPage
import javax.inject.Inject

class GetProductsByPageImpl @Inject constructor(private val apiService: ApiService) : GetProductsByPage {
    override suspend fun getProducts(numberPage: Int): List<Product> {
        val result = mutableListOf<Product>()
        val limit = 30 // items on the one page
        if (numberPage > 0) {
            val skip = (numberPage - 1) * limit // start item for requested page
            apiService.getProductsByPage(limit = limit, skip = skip).products.forEach { item ->
                result.add(item.toDomain())
            }
            return result.toList()
        } else {
            throw Exception("Number page should be positive")
        }
    }

}