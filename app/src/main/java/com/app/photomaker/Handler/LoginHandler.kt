package com.app.photomaker.Handler

import com.app.photomaker.Models.Login.LoginExample
import com.app.photomaker.Models.SignupExample

interface LoginHandler {
    fun onSuccess(loginExample: LoginExample?)
    fun onError(message: String?)
}