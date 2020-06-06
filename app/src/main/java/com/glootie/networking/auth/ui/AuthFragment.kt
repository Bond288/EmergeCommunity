package com.glootie.networking.auth.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.glootie.networking.R
import com.glootie.networking.auth.presentation.AuthViewModel
import com.glootie.networking.base.BaseFragment
import com.glootie.networking.qr.TicketScannerFragment
import com.glootie.networking.startup.ui.StartupListFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import java.util.*

class AuthFragment : BaseFragment() {

    companion object {

        const val SCAN_TICKET_RESULT_CODE = 101
        const val FACEBOOK_LOGIN_RESULT_CODE = 64206
        const val PERMISSION = "email"
    }

    private var isLogin = false

    private lateinit var viewModel: AuthViewModel
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        initFacebookAuthButton()
        initScannerButton()
    }


    private fun initFacebookAuthButton() {
        facebook_login.setOnClickListener { loginFacebook() }
    }

    private fun loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, arrayListOf(PERMISSION));
        LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result ?: return
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }
        })
    }

    private fun initScannerButton() {
        scanner.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = TicketScannerFragment()
            fragmentTransaction.add(R.id.fragment_container_view_tag, fragment)
            fragmentTransaction.commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        isLogin = when (requestCode) {
            SCAN_TICKET_RESULT_CODE -> resultCode == Activity.RESULT_OK
            FACEBOOK_LOGIN_RESULT_CODE -> resultCode == Activity.RESULT_OK
            else -> false
        }

        if (isLogin) {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = StartupListFragment()
            fragmentTransaction.add(R.id.fragment_container_view_tag, fragment)
            fragmentTransaction.commit()
        }
    }
}