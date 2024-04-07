package com.productsapp.domain.usecases.history

import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.PutHistoryItem

class PutHistoryUseCase(private val repository: PutHistoryItem) {
    fun execute(item: History) {
        repository.put(item)
    }
}