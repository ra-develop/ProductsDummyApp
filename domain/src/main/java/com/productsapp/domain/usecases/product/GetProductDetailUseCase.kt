package com.productsapp.domain.usecases.product

import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.domain.repositories.product.GetProductDetail

class GetProductDetailUseCase(private val repository: GetProductDetail) {
    suspend fun execute(productCode: String): Product {
        return repository.getDetail(productCode = productCode)
    }
}