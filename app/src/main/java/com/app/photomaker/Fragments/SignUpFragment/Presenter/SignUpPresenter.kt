package com.app.photomaker.Fragments.SignUpFragment.Presenter

import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Utils.CSPreferences
import com.app.photomaker.Activities.LoginActivity.LoginActivity
import com.app.photomaker.Fragments.SignUpFragment.SignUpFragment
import com.app.photomaker.Fragments.SignUpFragment.View.SignUpView
import com.app.photomaker.Handler.SignUpHandler
import com.app.photomaker.Models.SignupExample
import com.app.photomaker.Utils.Utils
import com.app.photomaker.Utils.WebServices

class SignUpPresenter(private val activity: FragmentActivity, private val signUpView: SignUpView) {


    fun signupMethod(
        et_username: EditText?,
        et_mail: EditText?,
        et_pass: EditText?,
    ) {


        if (Utils.isNetworkConnected(activity)) {
            signUpView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.signupMethod(
                et_username?.text?.trim().toString(),
                et_mail?.text?.trim().toString(),
                "122333333",
                et_pass?.text?.trim().toString(),
                "asasasasa",
                object : SignUpHandler {

                    override fun onSuccess(signupExample: SignupExample?) {
                        signUpView?.hideDialog()
                        if (signupExample != null) {
                            if (signupExample.getIsSuccess() === true) {

                                signUpView?.showMessage(activity, signupExample.getMessage())
                                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
                                activity.finish()

                            } else {
                                signUpView?.showMessage(activity, signupExample.getMessage())
                            }
                        }
                    }

                    override fun onError(message: String?) {
                        signUpView?.hideDialog()
                        signUpView?.showMessage(activity, message)
                    }
                })
        }else{
            Toast.makeText(activity,"Please Check Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }

}