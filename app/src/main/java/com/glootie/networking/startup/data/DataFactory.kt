package com.glootie.networking.startup.data

import com.glootie.networking.R
import com.glootie.networking.startup.domain.model.StartupInfo

object DataFactory {

    val STARTUP_LIST = mutableListOf(
        StartupInfo(
            "Emerge Afterparty",
            "Толпы стартаперов\n" +
                    "Десятки инвесторов\n" +
                    "Море алкоголя\n" +
                    "\n" +
                    "Отдохни после длительной конференции, заведи новые связи в формате нашего онлайн-бара!",
            R.raw.leadersofdigital_demo_afterparty,
            R.string.first_startup_call_link
        ),
        StartupInfo(
            "TatooMasks",
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
            R.raw.leadersofdigital_demo_startup_tatoo,
            R.string.second_startup_call_link
        ),
        StartupInfo(
            "MegaPonchik",
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
            R.raw.leadersofdigital_demo_startup_ponchik,
            R.string.third_startup_call_link
        )
    )
}