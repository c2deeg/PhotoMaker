package com.app.photomaker.Utils

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

//class PhotoApp : Application(), MultiDexApplication() {
class PhotoApp :  MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        photoApp = this
        WebServices().create()
        MultiDex.install(applicationContext)
    }

    companion object {
        var photoApp: PhotoApp? = null
            private set
        private val TAG = PhotoApp::class.java.simpleName


    }
}