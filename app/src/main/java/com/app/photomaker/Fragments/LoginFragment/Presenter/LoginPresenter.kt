package com.app.photomaker.Fragments.LoginFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Utils.CSPreferences
import com.app.photomaker.Fragments.LoginFragment.LoginFragment
import com.app.photomaker.Fragments.LoginFragment.View.LoginView
import com.app.photomaker.Handler.LoginHandler
import com.app.photomaker.Models.Login.LoginExample
import com.app.photomaker.Utils.Utils
import com.app.photomaker.Utils.WebServices

class LoginPresenter(private val activity: FragmentActivity, private val loginView: LoginView) {


    private lateinit var et_pass: String
    private lateinit var et_mail: String

    fun loginMethod(et_mail: String, et_pass: String) {
        this.et_mail = et_mail
        this.et_pass = et_pass
        var token: String? = null
        if (Utils.isNetworkConnected(activity!!)) {
            if (checkValidation())
                loginView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.loginMethod(et_mail, et_pass, token, object :
                LoginHandler {

                override fun onSuccess(loginExample: LoginExample?) {
                    loginView?.hideDialog()
                    if (loginExample != null) {
                        if (loginExample.getIsSuccess() === true) {
                            CSPreferences.putString(activity, Utils.USERLOGIN, "true")
                            Toast.makeText(activity, "LoginSuccessfully", Toast.LENGTH_SHORT).show()
                            activity.finish()


//
                            CSPreferences.putString(activity, Utils.USERID, loginExample.data.id)
                            loginView?.showMessage(activity, loginExample.getMessage())



                        } else {
                            loginView?.showMessage(activity, loginExample.getMessage())
                        }
                    } else {
                        loginView?.showMessage(activity, loginExample?.getMessage())
                    }
                }

                override fun onError(message: String?) {
                    loginView?.hideDialog()
                    loginView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    private fun checkValidation(): Boolean {
        if (et_mail?.length == 0) {
            return false
        } else if (et_pass?.length == 0) {
            return false
        }
        return true
    }

}