package com.productsapp.core.utils

import com.productsapp.domain.entities.product.dummyJson.Product

fun viewPriceCalculation(item: Product): String {
    return if (item.discountPercentage != 0.0) {
        val discountedPrice = item.price * (1 - item.discountPercentage / 100)
        discountedPrice.toInt().toString()
    } else {
        item.price.toString()
    }
}