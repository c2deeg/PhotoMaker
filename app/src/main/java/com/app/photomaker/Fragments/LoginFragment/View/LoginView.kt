package com.app.photomaker.Fragments.LoginFragment.View

import android.app.Activity

interface LoginView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}