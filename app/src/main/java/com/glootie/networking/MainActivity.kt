package com.glootie.networking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.glootie.networking.base.BaseActivity
import com.glootie.networking.qr.TicketScannerActivity


class MainActivity : BaseActivity() {

    private companion object {
        private val SCAN_TICKET_RESULT_CODE = 101
        private val FACEBOOK_LOGIN_RESULT_CODE = 202
    }

    private var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun scanTicket(view: View) {
        startActivityForResult(Intent(this@MainActivity, TicketScannerActivity::class.java), SCAN_TICKET_RESULT_CODE)
    }

    fun facebookLogin(view: View) {
        // TODO: 06.06.2020 facebook startActivityForResult with FACEBOOK_LOGIN_RESULT_CODE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        isLogin = when (requestCode) {
            SCAN_TICKET_RESULT_CODE -> resultCode == Activity.RESULT_OK
            FACEBOOK_LOGIN_RESULT_CODE -> resultCode == Activity.RESULT_OK
            else -> false
        }
    }
}