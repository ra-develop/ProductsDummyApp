package com.productsapp.domain.entities.product.dummyJson

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val brand: String = "",
    val category: String = "",
    val description: String = "",
    val discountPercentage: Double = 0.0,
    val id: Int = 0,
    val images: List<String> = listOf(),
    val price: Int = 0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val thumbnail: String = "",
    val title: String = ""
)