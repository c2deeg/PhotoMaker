package com.app.photomaker.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

import com.app.photomaker.R
import com.app.photomaker.SplashActivity2

class PlanMemberShipActivity : AppCompatActivity(), View.OnClickListener {
    //    lateinit var img_next: ImageView
    lateinit var img_cancel: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_member_ship)
        init()
        listners()
    }

    private fun init() {
        img_cancel = findViewById(R.id.img_cancel)

    }

    private fun listners() {
        img_cancel.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        if (p0 == img_cancel) {
            finish()
        }
    }

}