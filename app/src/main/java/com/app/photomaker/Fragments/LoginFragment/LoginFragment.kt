package com.app.photomaker.Fragments.LoginFragment

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.photomaker.Fragments.LoginFragment.Presenter.LoginPresenter
import com.app.photomaker.Fragments.LoginFragment.View.LoginView
import com.app.photomaker.Fragments.SignUpFragment.SignUpFragment
import com.app.photomaker.R
import com.app.photomaker.Utils.Utils
import com.facebook.CallbackManager
import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginFragment : Fragment(), View.OnClickListener,LoginView {
    var tv_createaccount:TextView?=null
    var btn_google:Button?=null
    var et_email:EditText?=null
    var et_pass:EditText?=null
    var login_button:LoginButton?=null
    var btn_fb:Button?=null
    private val RC_SIGN_IN = 1
    var mGoogleSignInClient: GoogleSignInClient? = null
    var callbackManager:CallbackManager?=null
    var btn_login:Button?=null
    private var loginPresenter:LoginPresenter?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        inits(view)
        listenrs(view)
        //        updateUI(account);
        FacebookSdk.sdkInitialize(requireActivity()!!.applicationContext)
        AppEventsLogger.activateApp(requireActivity()!!.application)
        callbackManager = create()

        login_button!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {


            override fun onCancel() {}
            override fun onError(e: FacebookException) {}
            override fun onSuccess(result: LoginResult?) {

            }
        })



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(activity as FragmentActivity, gso);
        loginPresenter = LoginPresenter(activity as FragmentActivity,this)



        return view
    }



    private fun inits(view: View?) {
        tv_createaccount = view?.findViewById(R.id.tv_createaccount)
        btn_google = view?.findViewById(R.id.btn_google)
        btn_fb = view?.findViewById(R.id.btn_fb)
        login_button = view?.findViewById(R.id.login_button)
        et_email = view?.findViewById(R.id.et_email)
        et_pass = view?.findViewById(R.id.et_pass)
        btn_login = view?.findViewById(R.id.btn_login)
    }
    private fun listenrs(view: View?) {

        tv_createaccount?.setOnClickListener(this)
        btn_google?.setOnClickListener(this)
        btn_fb?.setOnClickListener(this)
        login_button?.setOnClickListener(this)
        btn_login?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0==tv_createaccount){
            Utils.changeloginFragment(activity as FragmentActivity, SignUpFragment())
        }else if(p0==btn_google){
            signIn()
            activity?.finish()
        }else if (p0==btn_fb){
            login_button!!.performClick()
            activity?.finish()
            Toast.makeText(activity,"Login Successfully", Toast.LENGTH_SHORT).show()


        }else if(p0==btn_login){

            loginPresenter?.loginMethod(et_email?.text.toString(), et_pass?.text.toString())
        }
    }



    private fun signIn() {
        try {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), e.message, Toast.LENGTH_SHORT).show()
        }

    }


    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(activity as FragmentActivity)

//        updateUI(account)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(activity,"Login Successfully", Toast.LENGTH_LONG).show()


//            googleusername = account.displayName
//            googleusernamemail = account.email
//            googleuserid = account.id
//            Log.d("soiald", googleuserid.toString())
//            loginPresenter?.sociallogin(
//                googleuserid!!,
//                "google",
//                googleusernamemail!!,
//                googleusername!!,
//                ""
//            )

            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            Toast.makeText(activity,"ff", Toast.LENGTH_LONG).show()

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(ContentValues.TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity()!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }



}