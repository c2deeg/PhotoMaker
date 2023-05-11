package com.app.photomaker.Utils

import com.app.photomaker.Models.Login.LoginExample
import com.app.photomaker.Models.SignupExample
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("users/create")
    fun signupAPI(@Body jsonObject: JsonObject?): Call<SignupExample>

    //LoginAPI
    @POST("users/login")
    fun logAPI(@Body jsonObject: JsonObject?): Call<LoginExample?>?
}