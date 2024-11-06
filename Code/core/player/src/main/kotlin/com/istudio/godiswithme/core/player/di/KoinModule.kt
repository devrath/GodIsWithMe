package com.istudio.godiswithme.core.player.di

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import com.istudio.godiswithme.core.player.service.JetAudioService
import org.koin.dsl.module

@SuppressLint("UnsafeOptInUsageError")
val mediaModule = module {
    // Audio attributes object
    single {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    // ExoPlayer Instance
    single {
        ExoPlayer.Builder(get())
            .setAudioAttributes(get(), true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(get()))
            .build()
    }

    // MediaSession Instance
    single {
        MediaSession.Builder(get(), get<ExoPlayer>())
            .build()
    }

    // MediaSession Service Instance
    single {
        JetAudioService(get())
    }

    // Notification manager compact
    single {
        NotificationManagerCompat.from(get())
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