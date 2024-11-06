package com.istudio.godiswithme.core.player.service

import androidx.media3.common.Player
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import org.koin.android.ext.android.inject

/**
 * Using this service we can expose the media session to the Android-OS notification
 */
class JetAudioService : MediaSessionService() {

    private val mediaSession: MediaSession by inject()

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession =
        mediaSession


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