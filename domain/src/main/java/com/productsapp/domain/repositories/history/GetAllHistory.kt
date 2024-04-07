package com.productsapp.domain.repositories.history

import com.productsapp.domain.entities.History

interface GetAllHistory {
    fun getAll(): List<History>
}