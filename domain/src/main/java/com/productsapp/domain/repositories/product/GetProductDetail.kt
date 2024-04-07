package com.productsapp.domain.repositories.product

import com.productsapp.domain.entities.product.dummyJson.Product

interface GetProductDetail {
    suspend fun getDetail(productCode: String): Product

}