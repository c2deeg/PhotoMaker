package com.app.photomaker.Activities.LoginActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.app.photomaker.Fragments.LoginFragment.LoginFragment
import com.app.photomaker.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loginFragment= LoginFragment()
        var loginAc_Frame = findViewById<FrameLayout>(R.id.loginAc_Frame)
        setCurrentFragment(loginFragment)

    }
    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginAc_Frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}