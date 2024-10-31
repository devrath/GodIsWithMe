package com.istudio.godiswithme.application

import android.app.Application
import com.istudio.godiswithme.core.logger.di.loggerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(loggerModule)
        }

    }


}

