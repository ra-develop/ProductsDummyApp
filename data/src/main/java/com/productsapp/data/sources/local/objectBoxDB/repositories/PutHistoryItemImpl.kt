package com.productsapp.data.sources.local.objectBoxDB.repositories

import com.productsapp.data.sources.local.objectBoxDB.init.ObjectBoxInstance
import com.productsapp.data.sources.local.objectBoxDB.mappers.toData
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO_
import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.PutHistoryItem
import java.util.Date

class PutHistoryItemImpl : PutHistoryItem {
    private val historyBox = ObjectBoxInstance.store.boxFor(HistoryDAO::class.java)

    override fun put(item: History) {
        val query = historyBox.query(
            HistoryDAO_.product.contains("\"id\":${item.product.id}")
        ).build()
        val result = query.find()
        query.close()
        if (result.isNotEmpty()) {
            val itemDAO = result.first()
            itemDAO.accessDate = Date()
            historyBox.put(itemDAO)
        } else {
            historyBox.put(item.toData())
        }
    }

}
