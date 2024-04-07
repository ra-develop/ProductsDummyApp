package com.productsapp.data.sources.remote.models.mappers.dummyJson

import com.productsapp.data.sources.remote.models.product.dummyJson.ProductDTO
import com.productsapp.domain.entities.product.dummyJson.Product

fun ProductDTO.toDomain(): Product {
    return Product(
        brand = this.brand,
        category = this.category,
        description = this.description,
        discountPercentage = this.discountPercentage,
        id = this.id,
        images = this.images,
        price = this.price,
        rating = this.rating,
        stock = this.stock,
        thumbnail = this.thumbnail,
        title = this.title
    )
}