package com.istudio.godiswithme.core.logger

import org.koin.dsl.module


val loggerModule = module { single { provideLogger() } }

internal fun provideLogger(): Logger = AppLogger()


