package com.productsapp.data.sources.local.objectBoxDB.init

import android.content.Context
import com.productsapp.data.sources.local.objectBoxDB.models.MyObjectBox
import io.objectbox.BoxStore

/**
 * Initiation the ObjectBox
 */
object ObjectBoxInstance {
    lateinit var store: BoxStore
        private set
    fun init(context: Context) {
        store = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}