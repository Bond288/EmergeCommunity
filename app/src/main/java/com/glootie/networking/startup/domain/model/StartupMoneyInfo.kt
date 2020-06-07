package com.glootie.networking.startup.domain.model

import java.io.Serializable

data class StartupMoneyInfo(
    val hasMoney: String,
    val needMoney: String
) : Serializable