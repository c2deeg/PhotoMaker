package com.app.photomaker.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.photomaker.Fragments.PlanFragment.PlanFragment
import com.app.photomaker.R
import com.google.android.material.navigation.NavigationView


class HomeActivity2 : AppCompatActivity() {
    var dLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.photomaker.R.layout.activity_home2)
        setNavigationDrawer(); // call method
    }

    private fun setNavigationDrawer() {
        dLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout // initiate a DrawerLayout
        val navView =
            findViewById<View>(R.id.navigation) as NavigationView // initiate a Navigation View
        // implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            var frag: Fragment? = null // create a Fragment Object
            val itemId = menuItem.itemId // get selected menu item's id
            // check selected menu item's id and replace a Fragment Accordingly
            if (itemId == R.id.first) {
                frag = PlanFragment()
            } else if (itemId == R.id.second) {
                frag = PlanFragment()
            } else if (itemId == R.id.third) {
                frag = PlanFragment()
            }
            // display a toast message with menu item's title
            Toast.makeText(applicationContext, menuItem.title, Toast.LENGTH_SHORT).show()
            if (frag != null) {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.content_frame, frag) // replace a Fragment with Frame Layout
                transaction.commit() // commit the changes
                dLayout?.closeDrawers() // close the all open Drawer Views
                return@OnNavigationItemSelectedListener true
            }
            false
        })
    }
}