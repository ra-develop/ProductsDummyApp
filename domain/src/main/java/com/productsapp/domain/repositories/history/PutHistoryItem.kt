package com.productsapp.domain.repositories.history

import com.productsapp.domain.entities.History

interface PutHistoryItem {
    fun put(item: History)
}