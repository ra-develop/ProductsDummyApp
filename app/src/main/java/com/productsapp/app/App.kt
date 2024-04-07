package com.productsapp.app

import android.app.Application
import com.productsapp.data.sources.local.objectBoxDB.init.ObjectBoxInstance
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // init timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // Initiate the ObjectBox DataBase
        ObjectBoxInstance.init(this)
    }
}
