package com.productsapp.domain.usecases.product

import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.domain.repositories.product.GetProductsByPage

class GetProductsByPageUseCase(private val repository: GetProductsByPage) {
    suspend fun execute( numberPage: Int): List<Product> {
        return repository.getProducts(numberPage = numberPage)
    }
}