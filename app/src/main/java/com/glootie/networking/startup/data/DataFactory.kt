package com.glootie.networking.startup.data

import com.glootie.networking.R
import com.glootie.networking.startup.domain.model.StartupInfo
import com.glootie.networking.startup.domain.model.StartupMoneyInfo
import com.glootie.networking.startup.domain.model.StartupQuickDescription

object DataFactory {

    val STARTUP_LIST = mutableListOf(
        StartupInfo(
            "Emerge Afterparty",
            StartupQuickDescription(
                "Отдохни после длительной конференции, заведи новые связи в формате нашего онлайн-бара!",
                R.string.startup1_quick_info_money_value,
                "человек уже идут"
            ),
            "Толпы стартаперов\n" +
                    "Десятки инвесторов\n" +
                    "Море алкоголя\n" +
                    "\n" +
                    "Отдохни после длительной конференции, заведи новые связи в формате нашего онлайн-бара!",
            null,
            R.drawable.afterparty_image,
            R.raw.leadersofdigital_demo_afterparty,
            R.string.first_startup_call_link
        ),
        StartupInfo(
            "TatooMasks",
            StartupQuickDescription(
                "Ты слишком скучный, приходи к нам мы сделаем тебя веселее. Навсегда. ",
                R.string.startup2_quick_info_money_value,
                "Долевое участие до 30%"
            ),
            "<b>Миссия:</b> набивать маски всем на лицо\n" +
                    "\n" +
                    "<b>Проблема, которую решаем</b>\n" +
                    "Ты слишком скучный, приходи к нам мы сделаем тебя веселее. Навсегда. \n" +
                    "\n" +
                    "<b>Привлечено инвестиций</b>\n" +
                    "100 руб с карты основателя\n" +
                    "\n" +
                    "<b>На что нужны деньги</b>\n" +
                    "На покупку машинки",
            StartupMoneyInfo("200", "350т"),
            R.drawable.mask_images,
            R.raw.leadersofdigital_demo_startup_tatoo,
            R.string.second_startup_call_link
        ),
        StartupInfo(
            "MegaPonchik",
            StartupQuickDescription(
                "Слишком маленькие пончики вокруг, нужно больше пончиков богу пончиков",
                R.string.startup3_quick_info_money_value,
                "Долевое участие до 30%"
            ),
            "<b>Миссия:</b>" +
                    "\nнабивать желудки \n" +
                    "\n" +
                    "<b>Проблема, которую решаем</b>\n" +
                    "Слишком маленькие пончики \n" +
                    "\n" +
                    "<b>Привлечено инвестиций</b>\n" +
                    "Хватит на 1 пончик\n" +
                    "\n" +
                    "<b>На что нужны деньги</b>\n" +
                    "На покупку сахара. Нужно больше сахара!",
            StartupMoneyInfo("100т", "450т"),
            0,
            R.raw.leadersofdigital_demo_startup_ponchik,
            R.string.third_startup_call_link
        )
    )
}