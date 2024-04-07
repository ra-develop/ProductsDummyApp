package com.productsapp.domain.usecases.history

import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.GetAllHistory

class GetAllHistoryUseCase(private val repository: GetAllHistory) {
    fun execute(): List<History> {
        return repository.getAll()
    }
}