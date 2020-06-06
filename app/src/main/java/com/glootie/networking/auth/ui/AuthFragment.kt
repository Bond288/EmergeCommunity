package com.glootie.networking.auth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.glootie.networking.R
import com.glootie.networking.auth.presentation.AuthViewModel
import com.glootie.networking.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : BaseFragment() {

    companion object {

        const val PERMISSION = "email"
    }

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
    }


    private fun initFacebookAuthButton() {
        facebook_login_button.setPermissions(arrayListOf(PERMISSION))
        facebook_login_button.fragment = this
        facebook_login_button.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {
                result ?: return
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}