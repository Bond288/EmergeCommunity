package com.glootie.networking.startup.domain.model

import java.io.Serializable

data class StartupInfo(
    val title: String,
    val quickDescription: StartupQuickDescription,
    val description: String,
    val moneyInfo: StartupMoneyInfo?,
    val imageRes: Int,
    val videoUrl: Int,
    val callLinkRes: Int
) : Serializable