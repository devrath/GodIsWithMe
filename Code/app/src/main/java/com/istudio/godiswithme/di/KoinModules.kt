package com.istudio.godiswithme.di

import com.istudio.godiswithme.architecture.data.repository.GodRepositoryImpl
import com.istudio.godiswithme.architecture.data.repository.LanguageRepositoryImpl
import com.istudio.godiswithme.architecture.data.services.language.LanguageHelperService
import com.istudio.godiswithme.architecture.data.services.language.LanguageHelperServiceImpl
import com.istudio.godiswithme.architecture.data.services.localdata.LocalRepositoryService
import com.istudio.godiswithme.architecture.data.services.localdata.LocalRepositoryServiceImpl
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.architecture.domain.repository.LanguageRepository
import com.istudio.godiswithme.architecture.domain.usecases.GetGodByNameUseCase
import com.istudio.godiswithme.architecture.domain.usecases.GetGodSongsByNameUseCase
import com.istudio.godiswithme.architecture.domain.usecases.GetGodsListUseCase
import com.istudio.godiswithme.architecture.features.home.audio.AudioVm
import com.istudio.godiswithme.architecture.features.home.gods.goddetails.GodDetailsVm
import com.istudio.godiswithme.core.logger.di.crashlyticsLoggerModule
import com.istudio.godiswithme.core.logger.di.firebaseModule
import com.istudio.godiswithme.core.logger.di.loggerModule
import com.istudio.godiswithme.core.logger.di.timberDebugModule
import com.istudio.godiswithme.core.logger.di.timberInitializationModule
import com.istudio.godiswithme.core.logger.di.timberReleaseModule
import com.istudio.godiswithme.architecture.features.home.gods.godgallery.GodGalleryVm
import com.istudio.godiswithme.architecture.features.home.gods.god.GodScreenVm
import com.istudio.godiswithme.architecture.features.home.gods.godSongs.GodSongsVm
import com.istudio.godiswithme.architecture.features.home.settings.SettingsScreenVm
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.main.MainVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale

val appModule = module{
    single { AssetManager(get()) }
    single { Locale.getDefault() }
    single { GetGodsListUseCase(get()) }
    single { GetGodByNameUseCase(get()) }
    single { GetGodSongsByNameUseCase(get()) }
    single<GodRepository> { GodRepositoryImpl(get()) }
    single<LanguageRepository> { LanguageRepositoryImpl(get()) }
    single<LocalRepositoryService> { LocalRepositoryServiceImpl(get(),get(),get()) }
    single<LanguageHelperService> { LanguageHelperServiceImpl(get()) }
}

val viewModule = module {
    viewModel { MainVm(get()) }
    viewModel { GodGalleryVm(get(),get(),get()) }
    viewModel { GodScreenVm(get(),get(),get()) }
    viewModel { GodDetailsVm(get(),get(),get()) }
    viewModel { GodSongsVm(get(),get(),get()) }
    viewModel { AudioVm(get(),get(),get(),get(),get()) }
    viewModel { SettingsScreenVm(get(),get()) }
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