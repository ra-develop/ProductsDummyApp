package com.productsapp.feature.products.ui.viewModels

import com.productsapp.domain.entities.product.dummyJson.Product

/**
 * Data class that represents the UI state
 */
data class ProductsUiState(
    val items: List<Product> = listOf()
)
