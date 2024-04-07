package com.productsapp.data.sources.local.objectBoxDB.repositories

import com.productsapp.data.sources.local.objectBoxDB.init.ObjectBoxInstance
import com.productsapp.data.sources.local.objectBoxDB.mappers.toDomain
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO
import com.productsapp.data.sources.local.objectBoxDB.models.HistoryDAO_
import com.productsapp.domain.entities.History
import com.productsapp.domain.repositories.history.GetAllHistory
import io.objectbox.query.QueryBuilder

class GetAllHistoryImpl : GetAllHistory {
    private val historyBox = ObjectBoxInstance.store.boxFor(HistoryDAO::class.java)
    override fun getAll(): List<History> {
        val query = historyBox
            .query(HistoryDAO_.accessDate.notNull())
            .order(HistoryDAO_.accessDate, QueryBuilder.DESCENDING)
            .build()
        val result = query.find()
        query.close()
        return result.map { it.toDomain() }
    }
}