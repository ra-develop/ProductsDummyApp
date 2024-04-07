package com.productsapp.domain.entities

import com.productsapp.domain.entities.product.dummyJson.Product
import java.util.Date

data class History (
    val product: Product,
    val accessDate: Date = Date()
)