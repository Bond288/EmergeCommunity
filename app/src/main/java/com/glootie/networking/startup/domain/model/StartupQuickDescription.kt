package com.glootie.networking.startup.domain.model

import java.io.Serializable

data class StartupQuickDescription(
    val description: String,
    val moneyValueRes: Int,
    val moneyHow: String
) : Serializable