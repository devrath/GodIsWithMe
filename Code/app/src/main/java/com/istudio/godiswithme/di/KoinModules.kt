package com.istudio.godiswithme.di

import com.istudio.godiswithme.core.logger.di.crashlyticsLoggerModule
import com.istudio.godiswithme.core.logger.di.firebaseModule
import com.istudio.godiswithme.core.logger.di.loggerModule
import com.istudio.godiswithme.core.logger.di.timberDebugModule
import com.istudio.godiswithme.core.logger.di.timberInitializationModule
import com.istudio.godiswithme.core.logger.di.timberReleaseModule
import com.istudio.godiswithme.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel { MainViewModel() }
}

val loggerParentModule = module {
    includes(
        loggerModule, timberDebugModule, timberReleaseModule, timberInitializationModule
    )
}

val firebaseParentModule = module {
    includes(
        firebaseModule, crashlyticsLoggerModule
    )
}