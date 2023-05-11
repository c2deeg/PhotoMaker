package com.app.photomaker.Activities

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.app.photomaker.R
import com.app.photomaker.SplashActivity2
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var img_next: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listners()

        try {
            val info: PackageInfo = this.getPackageManager().getPackageInfo(
                "com.app.photomaker",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d(
                    "KeyHash", "KeyHash:" + Base64.encodeToString(
                        md.digest(),
                        Base64.DEFAULT
                    )
                )
                Log.e("SHA1", md.toString())
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }

    }

    private fun init() {
        img_next = findViewById(R.id.img_next)

    }

    private fun listners() {
        img_next.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == img_next) {
            var intent = Intent(this, SplashActivity2::class.java)
            startActivity(intent)
        }
    }
}