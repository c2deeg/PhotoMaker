package com.app.photomaker.Activities.SplashProfileActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.app.photomaker.Activities.HomeActivity.HomeActivity
import com.app.photomaker.R

class SplashProfileActivity : AppCompatActivity(), View.OnClickListener {
    var img_arrow:ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_profile)
        init()
        listeners()
    }
    private fun init(){
        img_arrow  = findViewById(R.id.img_arrow)
    }
    private fun listeners(){
        img_arrow?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0==img_arrow){
            var intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)

        }
    }
}