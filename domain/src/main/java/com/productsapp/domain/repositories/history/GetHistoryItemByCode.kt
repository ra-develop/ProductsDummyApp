package com.productsapp.domain.repositories.history

import com.productsapp.domain.entities.History

interface GetHistoryItemByCode {
    fun getItemByCode(code: String): History
}