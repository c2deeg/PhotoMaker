package com.app.photomaker.Activities.HomeActivity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.app.photomaker.Activities.CreatePostActivity.CreatePostActivity
import com.app.photomaker.Activities.PlanMemberShipActivity
import com.app.photomaker.Fragments.FavourtieFragment.FavourtieFragment
import com.app.photomaker.Fragments.HomeFragment.HomeFragment
import com.app.photomaker.Fragments.LoginFragment.LoginFragment
import com.app.photomaker.Fragments.PlanFragment.PlanFragment
import com.app.photomaker.Fragments.ProjectJavaFragment
import com.app.photomaker.R
import com.app.photomaker.Utils.Utils
import com.facebook.CallbackManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.switchmaterial.SwitchMaterial


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bottomnav: BottomNavigationView
    lateinit var img_addbutton: ImageButton
    val homeFragment = HomeFragment()
    val projecrFragment = ProjectJavaFragment()
    var planFragment = PlanFragment()
    var favourtieFragment = FavourtieFragment()
    var bottomSheetDialog: BottomSheetDialog? = null
    var tv_createstory: TextView? = null
    var tv_capture: TextView? = null
    var tv_createpost: TextView? = null
    var PICK_IMAGE_MULTIPLE = 1
    var imageEncoded: String? = null
    var imagesEncodedList: List<String>? = null
    lateinit var tv_trail: TextView
    var viewPager: ViewPager? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var img_drawer: ImageView
    var mSlideState = false;
    lateinit var switchtogglebutton: SwitchCompat
    lateinit var themeSwitch: SwitchMaterial
    lateinit var navigationview: NavigationView
    var checkval: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var callbackManager = CallbackManager.Factory.create();
        init()
        listener()

//        val pref: SharedPreferences = this.getSharedPreferences("save", MODE_PRIVATE)
//        switchtogglebutton.setChecked(pref.getBoolean("first", false))



        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout, R.string.nav_open, R.string.nav_close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        setCurrentFragment(homeFragment)

        switchbuttononoff()

        navigationview.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            var frag: Fragment? = null // create a Fragment Object
            val itemId = menuItem.itemId // get selected menu item's id
            // check selected menu item's id and replace a Fragment Accordingly
            if (itemId == R.id.nav_account) {
                frag = LoginFragment()
            } else if (itemId == R.id.nav_settings) {
                val uri =
                    Uri.parse("https://cloud.google.com/contact") // missing 'http://' will cause crashed

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else if (itemId == R.id.nav_logout) {
                val uri =
                    Uri.parse("https://cloud.google.com/contact") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else if (itemId == R.id.nav_logout) {
                val uri =
                    Uri.parse("https://support.google.com/maps/answer/6230175?hl=en&co=GENIE.Platform%3DAndroid") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else if (itemId == R.id.privacypolicy) {

                val uri =
                    Uri.parse("https://amberclubpro.com/privacy-policy.html") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else if (itemId == R.id.tv_dismiss) {
                drawerLayout.close()
            }


            if (frag != null) {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.add(android.R.id.content, frag) // replace a Fragment with Frame Layout
                transaction.commit() // commit the changes
                drawerLayout?.closeDrawers() // close the all open Drawer Views
                return@OnNavigationItemSelectedListener true
            }
            false
        })



        bottomnav.setBackgroundDrawable(null)
        bottomnav?.setOnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.home ->
                    setCurrentFragment(homeFragment)

                R.id.project -> setCurrentFragment(projecrFragment)
                R.id.plan -> setCurrentFragment(planFragment)
                R.id.favorite -> setCurrentFragment(favourtieFragment)

            }
            true

        }


    }

    private fun init() {
        bottomnav = findViewById(R.id.bottomnav)
        img_addbutton = findViewById(R.id.img_addbutton)
        img_drawer = findViewById(R.id.img_drawer)!!
        switchtogglebutton = findViewById(R.id.switchtogglebutton)!!
        navigationview = findViewById(R.id.navigationview)!!
        tv_trail = findViewById(R.id.tv_trail)!!

    }

    private fun listener() {
        img_addbutton.setOnClickListener(this)
        img_drawer.setOnClickListener(this)
        tv_trail.setOnClickListener(this)
//        navigationview.setNavigationItemSelectedListener(this)

    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onClick(p0: View?) {
        if (p0 == img_addbutton) {
            showBottomSheetDialog()
        } else if (p0 == tv_createstory) {
            bottomSheetDialog?.dismiss()
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_MULTIPLE
            )
        } else if (p0 == tv_capture) {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        } else if (p0 == tv_createpost) {
            bottomSheetDialog?.dismiss()
            var intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        } else if (p0 == img_drawer) {
            clickEventSlide()

        } else if (p0 == tv_trail) {
            var intent = Intent(this, PlanMemberShipActivity::class.java)
            startActivity(intent)
        } else if (p0 == tv_createstory) {
            var intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }


    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(this, R.style.SheetDialog)
        bottomSheetDialog?.setContentView(R.layout.bottom_sheet_dialog)
        bottomSheetDialog?.show()
        tv_createstory = bottomSheetDialog?.findViewById<TextView>(R.id.tv_createstory)
        tv_capture = bottomSheetDialog?.findViewById<TextView>(R.id.tv_capture)
        tv_createpost = bottomSheetDialog?.findViewById<TextView>(R.id.tv_createpost)

        tv_createstory?.setOnClickListener(this)
        tv_capture?.setOnClickListener(this)
        tv_createpost?.setOnClickListener(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }


    fun clickEventSlide() {
        if (mSlideState) {
            drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            drawerLayout.openDrawer(Gravity.LEFT)
        }
    }

    fun switchbuttononoff() {
        switchtogglebutton.setOnCheckedChangeListener { _, isChecked ->
            if (switchtogglebutton.isChecked) {
//                val editor: SharedPreferences.Editor =
//                    this.getSharedPreferences("save", MODE_PRIVATE).edit()
//                editor.putBoolean("first", true)
//                editor.apply()
                switchtogglebutton.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                Utils.showDialogMethod(activity, activity?.fragmentManager)
//                Handler(Looper.getMainLooper()).postDelayed(
//                    {
//
//                        Utils.hideDialog()
//                    },
//                    2000
//                )

            } else {
//                val editor: SharedPreferences.Editor =
//                    this.getSharedPreferences("save", MODE_PRIVATE).edit()
//                editor.putBoolean("first", false)
//                editor.apply()
                switchtogglebutton.setChecked(false)

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


//                CSPreferences.putString(this, Utils.ROLE, "false")
//                Utils.showDialogMethod(activity, activity?.fragmentManager)
                Log.d("sdsdsds", "dsdsds")
//                Handler(Looper.getMainLooper()).postDelayed({
//                    Utils.hideDialog()
//
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//
//                }, 2000)

            }

        }


    }


}