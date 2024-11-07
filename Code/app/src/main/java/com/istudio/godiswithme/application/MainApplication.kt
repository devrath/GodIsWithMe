package com.istudio.godiswithme.application

import android.app.Application
import com.istudio.godiswithme.core.logger.applogger.timber.TimberInit
import com.istudio.godiswithme.core.player.di.mediaModule
import com.istudio.godiswithme.di.appModule
import com.istudio.godiswithme.di.firebaseParentModule
import com.istudio.godiswithme.di.loggerParentModule
import com.istudio.godiswithme.di.viewModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin


const val APP_TAG = "myApplicationLogs"
class MainApplication : Application(), KoinComponent {

    companion object {
        private val LOG_TAG = MainApplication::class.simpleName
    }

    private val timber: TimberInit by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        timber.bootUp()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule,loggerParentModule,firebaseParentModule,viewModule,mediaModule)
        }
    }


}

