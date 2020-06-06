package com.glootie.networking.startup.item.ui

import android.content.Context
import android.net.Uri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.glootie.networking.startup.domain.model.StartupInfo
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource


class VideoPlayer(
    private val context: Context,
    lifecycle: Lifecycle
) : LifecycleEventObserver {

    private var mediaPlayer = SimpleExoPlayer.Builder(
        context,
        DefaultRenderersFactory(context)
    ).build()

    init {
        lifecycle.addObserver(this)
    }

    fun setDataSource(playerView: PlayerView, startupInfo: StartupInfo) {
        val uri: Uri = RawResourceDataSource.buildRawResourceUri(startupInfo.videoUrl)

        val audioSource = ExtractorMediaSource(
            uri,
            DefaultDataSourceFactory(context, "MyExoplayer"),
            DefaultExtractorsFactory(),
            null,
            null
        )

        mediaPlayer.prepare(audioSource)
        playerView.player = mediaPlayer
        mediaPlayer.repeatMode = Player.REPEAT_MODE_ONE
    }

    private fun play() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.playWhenReady = true
        }
    }

    private fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.playWhenReady = false
        }
    }

    private fun release() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> this.play()
            Lifecycle.Event.ON_PAUSE -> this.pause()
            Lifecycle.Event.ON_DESTROY -> this.release()
            else -> {
            }
        }
    }
}