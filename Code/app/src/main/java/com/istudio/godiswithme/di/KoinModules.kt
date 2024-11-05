package com.istudio.godiswithme.di

import com.istudio.godiswithme.architecture.domain.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.domain.GetGodsListUseCase
import com.istudio.godiswithme.core.logger.di.crashlyticsLoggerModule
import com.istudio.godiswithme.core.logger.di.firebaseModule
import com.istudio.godiswithme.core.logger.di.loggerModule
import com.istudio.godiswithme.core.logger.di.timberDebugModule
import com.istudio.godiswithme.core.logger.di.timberInitializationModule
import com.istudio.godiswithme.core.logger.di.timberReleaseModule
import com.istudio.godiswithme.architecture.features.gallery.image.main_pane.ImageGalleryMainPaneVm
import com.istudio.godiswithme.architecture.features.gallery.image.supporting_pane.ImageGallerySupportingPaneVm
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
    viewModel { ImageGalleryMainPaneVm(get(),get()) }
    viewModel { ImageGallerySupportingPaneVm(get(),get()) }
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