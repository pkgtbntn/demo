package com.example.demoapp

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.example.demoapp.di.resourceApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Kotpref
        Kotpref.init(this)

        // Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(resourceApp)
        }
    }
}