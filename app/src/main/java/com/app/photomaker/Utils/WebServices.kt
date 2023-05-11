package com.app.photomaker.Utils

import com.app.humanresource.Utils.SocketConnection
import com.app.photomaker.Handler.LoginHandler
import com.app.photomaker.Handler.SignUpHandler
import com.app.photomaker.Models.Login.LoginExample
import com.app.photomaker.Models.SignupExample
import com.google.gson.JsonObject
import okhttp3.Callback
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServices {


    private val TAG = "WebSrvices"
    private lateinit var api: API


    fun create() {
        retrofit =
            Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        api = retrofit.create(API::class.java)

    }


    companion object Factory1 {
        private lateinit var mInstance: WebServices

        private lateinit var retrofit: Retrofit

        val base_url = "http://93.188.167.68:8070/api/"

        //        val base_url = "http://93.188.167.68:8004/api/"
        internal var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()


        fun getInstance(): WebServices? {
            mInstance = WebServices()
            return mInstance
        }
    }

    fun apiCreate() {
        api = retrofit.create(API::class.java)
    }


    //SignupMethod
    fun signupMethod(
        name: String?,
        email: String?,
        phoneNo: String?,
        password: String?,
        deviceToken: String?,

        signUpHandler: SignUpHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("phoneNo", phoneNo)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("deviceToken", deviceToken)
        apiCreate()



        api?.signupAPI(jsonObject)?.enqueue(object : retrofit2.Callback<SignupExample> {
            override fun onResponse(
                call: Call<SignupExample?>,
                response: Response<SignupExample?>
            ) {
                if (response.code() == 200) {
                    signUpHandler.onSuccess(response.body())
                } else {
                    val responceData =
                        SocketConnection.convertStreamToString(response.errorBody()!!.byteStream())
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            signUpHandler.onError(message)
                        } else {
                            signUpHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<SignupExample?>, t: Throwable) {
                signUpHandler.onError(t.message)
            }

        })


    }

    fun loginMethod(email: String?, password: String?, token: String?, loginHandler: LoginHandler) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("deviceToken", token)

        apiCreate()
        api?.logAPI(jsonObject)?.enqueue(object : retrofit2.Callback<LoginExample?> {
            override fun onResponse(call: Call<LoginExample?>, response: Response<LoginExample?>) {
                var acesstoken: String? = null
                acesstoken = response.headers()["x-access-token"]
                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            loginHandler.onError(message)
                        } else {
                            loginHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<LoginExample?>, t: Throwable) {
                loginHandler.onError(t.message)
            }
        })
    }





}