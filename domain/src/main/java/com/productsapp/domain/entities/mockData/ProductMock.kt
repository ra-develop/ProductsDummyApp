package com.productsapp.domain.entities.mockData

import com.productsapp.domain.entities.product.dummyJson.Product

class ProductMock {
    val value = Product (
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        price = 549.0,
        discountPercentage = 12.96,
        rating = 4.69,
        stock = 94,
        brand = "Apple",
        category = "smartphones",
        thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/1/1.jpg",
            "https://cdn.dummyjson.com/product-images/1/2.jpg",
            "https://cdn.dummyjson.com/product-images/1/3.jpg",
            "https://cdn.dummyjson.com/product-images/1/4.jpg",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg")
    )

    val listValue = List(10) {index ->
        Product (
            id = index,
            title = "iPhone 9_$index",
            description = "An apple mobile which is nothing like apple",
            price = 549.0,
            discountPercentage = 12.96,
            rating = 4.69,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "https://cdn.dummyjson.com/product-images/$index/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/$index/1.jpg",
                "https://cdn.dummyjson.com/product-images/$index/2.jpg",
                "https://cdn.dummyjson.com/product-images/$index/3.jpg",
                "https://cdn.dummyjson.com/product-images/$index/4.jpg",
                "https://cdn.dummyjson.com/product-images/$index/thumbnail.jpg")
        )
    }
}