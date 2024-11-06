package com.istudio.godiswithme.core.player.di

import android.annotation.SuppressLint
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import org.koin.dsl.module

@SuppressLint("UnsafeOptInUsageError")
val mediaModule = module {
    single {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    single {
        ExoPlayer.Builder(get())
            .setAudioAttributes(get(), true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(get()))
            .build()
    }

    single {
        MediaSession.Builder(get(), get<ExoPlayer>())
            .build()
    }

    /*single {
        JetAudioNotificationManager(
            context = get(),
            exoPlayer = get()
        )
    }

    single {
        JetAudioServiceHandler(get())
    }*/
}