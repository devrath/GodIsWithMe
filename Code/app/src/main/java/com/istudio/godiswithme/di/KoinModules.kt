package com.istudio.godiswithme.di

import com.istudio.godiswithme.architecture.domain.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.architecture.features.home.gods.goddetails.GodDetailsVm
import com.istudio.godiswithme.core.logger.di.crashlyticsLoggerModule
import com.istudio.godiswithme.core.logger.di.firebaseModule
import com.istudio.godiswithme.core.logger.di.loggerModule
import com.istudio.godiswithme.core.logger.di.timberDebugModule
import com.istudio.godiswithme.core.logger.di.timberInitializationModule
import com.istudio.godiswithme.core.logger.di.timberReleaseModule
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryVm
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenVm
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.main.MainVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    single { AssetManager(get()) }
    single { GetGodsListUseCase(get(), get()) }
    single { GetGodByNameUseCase(get(), get()) }
}

val viewModule = module {
    viewModel { MainVm() }
    viewModel { GodGalleryVm(get(),get()) }
    viewModel { GodScreenVm(get(),get()) }
    viewModel { GodDetailsVm(get(),get()) }
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