package com.productsapp.data.sources.remote.models.product.dummyJson

data class ProductsDTO(
    val limit: Int = 0,
    val products: List<ProductDTO> = listOf(),
    val skip: Int = 0,
    val total: Int = 0
)