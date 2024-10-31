package com.istudio.godiswithme.core.logger.di

import com.istudio.godiswithme.core.logger.applogger.AppLogger
import com.istudio.godiswithme.core.logger.applogger.Logger
import org.koin.dsl.module


val loggerModule = module { single { provideLogger() } }

internal fun provideLogger(): Logger = AppLogger()


