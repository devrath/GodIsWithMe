package com.istudio.godiswithme.core.logger.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.initialize
import com.istudio.godiswithme.core.logger.applogger.crashlytics.CrashlyticsLogger
import com.istudio.godiswithme.core.logger.applogger.local.AppLogger
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.core.logger.applogger.timber.TimberDebugTree
import com.istudio.godiswithme.core.logger.applogger.timber.TimberInit
import com.istudio.godiswithme.core.logger.applogger.timber.TimberReleaseTree
import org.koin.dsl.module


val loggerModule = module { single { provideLogger() } }
val timberDebugModule = module { single { TimberDebugTree() } }
val timberReleaseModule = module { single { TimberReleaseTree(get()) } }
val timberInitializationModule = module { single { TimberInit(get(),get()) } }
val firebaseModule = module { single { provideCrashlytics(androidContext()) } }
val crashlyticsLoggerModule = module { single { CrashlyticsLogger(get()) } }

internal fun provideLogger(): Logger = AppLogger()


internal fun provideCrashlytics(context: Context): FirebaseCrashlytics {
    Firebase.initialize(context)
    return Firebase.crashlytics
}