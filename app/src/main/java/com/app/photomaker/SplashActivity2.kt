package com.app.photomaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class SplashActivity2 : AppCompatActivity(), View.OnClickListener {
    lateinit var img_next: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        init()
        listeners()
    }

    private fun init() {
        img_next = findViewById(R.id.img_next)

    }

    private fun listeners() {
        img_next.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == img_next) {
            var intent = Intent(this, SplashActivity3::class.java)
            startActivity(intent)

        }
    }
}