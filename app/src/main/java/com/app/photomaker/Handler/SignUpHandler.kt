package com.app.photomaker.Handler

import com.app.photomaker.Models.SignupExample

interface SignUpHandler {
    fun onSuccess(signupExample: SignupExample?)
    fun onError(message: String?)
}