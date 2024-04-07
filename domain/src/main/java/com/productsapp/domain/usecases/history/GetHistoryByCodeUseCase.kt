package com.productsapp.domain.usecases.history

import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.GetHistoryItemByCode

class GetHistoryByCodeUseCase(private val repository: GetHistoryItemByCode) {
    fun execute(code: String): History {
        return repository.getItemByCode(code)
    }
}