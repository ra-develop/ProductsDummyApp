package com.productsapp.data.sources.local.objectBoxDB.mappers

import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO
import com.productsapp.domain.entities.History
import kotlinx.serialization.json.Json

fun HistoryDAO.toDomain(): History {
    return History(
        product = Json.decodeFromString(this.product),
        accessDate = this.accessDate
    )
}

