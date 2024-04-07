package com.productsapp.data.sources.local.objectBoxDB.repositories

import com.productsapp.data.sources.local.objectBoxDB.init.ObjectBoxInstance
import com.productsapp.data.sources.local.objectBoxDB.mappers.toDomain
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO_
import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.GetHistoryItemByCode

class GetHistoryItemByCodeImpl : GetHistoryItemByCode {
    private val historyBox = ObjectBoxInstance.store.boxFor(HistoryDAO::class.java)
    override fun getItemByCode(code: String): History {
        val query = historyBox.query(
            HistoryDAO_.product.contains("\"id\":$code")
        ).build()
        val result = query.find()
        query.close()
        return result.first().toDomain()
    }

}