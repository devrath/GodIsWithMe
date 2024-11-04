package com.istudio.godiswithme.di

import android.content.Context
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.core.logger.di.crashlyticsLoggerModule
import com.istudio.godiswithme.core.logger.di.firebaseModule
import com.istudio.godiswithme.core.logger.di.loggerModule
import com.istudio.godiswithme.core.logger.di.timberDebugModule
import com.istudio.godiswithme.core.logger.di.timberInitializationModule
import com.istudio.godiswithme.core.logger.di.timberReleaseModule
import com.istudio.godiswithme.architecture.features.gallery.image.ImageGalleryScreenViewModel
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import com.istudio.godiswithme.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    single { AssetManager(get()) }
    single { GetGodsListUseCase(get(), get()) }
}

val viewModule = module {
    viewModel { MainViewModel() }
    viewModel { ImageGalleryScreenViewModel(get(),get(), get()) }
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