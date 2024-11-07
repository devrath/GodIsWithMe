package com.istudio.godiswithme.core.player.service

import android.content.Intent
import android.os.Build
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.istudio.godiswithme.core.player.notification.JetAudioNotificationManager
import org.koin.android.ext.android.inject

/**
 * Using this service we can expose the media session to the Android-OS notification
 * Observe we can access player inside session without the connector code -> Lot of boilerplate code is reduced
 */
class JetAudioService() : MediaSessionService() {

    private val mediaSession: MediaSession by inject()
    private val notificationManager: JetAudioNotificationManager by inject()

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession =
        mediaSession

    @UnstableApi
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.startNotificationService(
                mediaSession = mediaSession,
                mediaSessionService = this
            )
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession.apply {
            // Release the session
            release()
            // If the playback state is Idle
            player.apply {
                if(playbackState != Player.STATE_IDLE){
                    seekTo(0)
                    playWhenReady = false
                    stop()
                }
            }
        }
    }


}