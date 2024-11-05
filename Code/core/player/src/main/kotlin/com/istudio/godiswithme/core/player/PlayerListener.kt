package com.istudio.godiswithme.core.player

import android.util.Size
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener

open class PlayerListener : Player.Listener {

/*

    internal open fun attachPlayer(player: ExoPlayer) {
        player.addListener(this)
        player.addAnalyticsListener(analyticsListener)
    }

    internal open fun detachPlayer(player: ExoPlayer) {
        player.removeListener(this)
        player.removeAnalyticsListener(analyticsListener)
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        playerInfoHolder = playerInfoHolder.copy(lastKnownPlayWhenReadyState = playWhenReady)
    }

    override fun onVideoSizeChanged(videoSize: VideoSize) {
        super.onVideoSizeChanged(videoSize)
        playerInfoHolder = playerInfoHolder.copy(lastVideoResolution = Size(videoSize.width, videoSize.height))
        emitMetric(
            PlaybackMetricEvent.VideoResolutionChanged(
                videoSize.width,
                videoSize.height
            )
        )
    }


    private val analyticsListener = object : AnalyticsListener {

    }*/
}