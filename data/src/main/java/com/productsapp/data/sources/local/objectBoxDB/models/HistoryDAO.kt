package com.productsapp.data.sources.local.objectBoxDB.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.Date


@Entity
data class HistoryDAO (
    @Id
    var id: Long = 0,
    val product: String = "",
    var accessDate: Date = Date()
)
