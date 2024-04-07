package com.productsapp.domain.repositories.product

import com.productsapp.domain.entities.product.dummyJson.Product

interface GetProductsByPage {
    suspend fun getProducts(numberPage: Int): List<Product>

}