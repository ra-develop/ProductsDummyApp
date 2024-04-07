package com.productsapp.data.sources.local.objectBoxDB.mappers

import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO
import com.productsapp.domain.entities.History
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun History.toData(): HistoryDAO {
    return HistoryDAO(
        product = Json.encodeToString(this.product),
        accessDate = this.accessDate
    )
}

