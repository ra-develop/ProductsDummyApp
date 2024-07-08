package com.productsapp.data.sources.remote.models.product.dummyJson

data class ProductDTO(
    val brand: String = "",
    val category: String = "",
    val description: String = "",
    val discountPercentage: Double = 0.0,
    val id: Int = 0,
    val images: List<String> = listOf(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val thumbnail: String = "",
    val title: String = ""
)