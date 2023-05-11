package com.app.photomaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Activities.SplashProfileActivity.SplashProfileActivity
import com.app.photomaker.Adapter.GridRecyclerAdapter


class SplashActivity3 : AppCompatActivity(), View.OnClickListener {
    lateinit var gridrecyclerview: RecyclerView
    var gridRecyclerAdapter: GridRecyclerAdapter? = null
    var img_next: ImageView? = null
    var activity: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash3)
        activity = this
        init()
        listeners()

        gridRecyclerAdapter = GridRecyclerAdapter(activity as SplashActivity3)
        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL //
        gridrecyclerview.layoutManager = gridLayoutManager

        gridrecyclerview.adapter = gridRecyclerAdapter
    }

    private fun init() {
        gridrecyclerview = findViewById(R.id.gridrecyclerview)
        img_next = findViewById(R.id.img_next)

    }

    private fun listeners() {
        img_next?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == img_next) {
            var intent = Intent(this, SplashProfileActivity::class.java)
            startActivity(intent)

        }
    }
}