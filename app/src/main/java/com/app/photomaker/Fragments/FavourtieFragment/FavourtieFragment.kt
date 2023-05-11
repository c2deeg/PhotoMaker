package com.app.photomaker.Fragments.FavourtieFragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.app.humanresource.Utils.CSPreferences
import com.app.photomaker.Activities.LoginActivity.LoginActivity
import com.app.photomaker.Adapter.BioSiteViewPagerAdapter
import com.app.photomaker.Fragments.LoginFragment.LoginFragment
import com.app.photomaker.R
import com.app.photomaker.Utils.Utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import me.relex.circleindicator.CircleIndicator

class FavourtieFragment : Fragment(), View.OnClickListener {
 var viewPage:ViewPager?=null
 var indicator:CircleIndicator?=null
 var btn_login:Button?=null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_favourtie, container, false)
        init(view)
        listeners(view)
        var bioSiteViewPagerAdapter = BioSiteViewPagerAdapter(activity)

        viewPage?.adapter = bioSiteViewPagerAdapter
        indicator?.setViewPager(viewPage)

        var value = CSPreferences.readString(activity as FragmentActivity,Utils.USERLOGIN)
        if (value.equals("true")){
            btn_login?.setText("View Details")

        }else{
            btn_login?.setText("Login")
        }


        return view
    }
    private fun init(view: View?){
        viewPage = view?.findViewById(R.id.viewPage)
        indicator = view?.findViewById(R.id.indicator)
        btn_login = view?.findViewById(R.id.btn_login)
    }

    private fun listeners(view: View?) {
        btn_login?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0==btn_login){
           var intent = Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
        }
    }







}