package com.productsapp.feature.products.ui.viewModels

import com.productsapp.domain.entities.History

data class HistoryUIState (
    val listHistory: List<History> = listOf()
)