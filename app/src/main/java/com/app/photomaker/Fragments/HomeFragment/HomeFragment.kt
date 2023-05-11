package com.app.photomaker.Fragments.HomeFragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.app.humanresource.Utils.CSPreferences
import com.app.photomaker.Adapter.TabslayoutsAdapter
import com.app.photomaker.Fragments.StoriesFragment.StoriesFragment
import com.app.photomaker.R
import com.app.photomaker.Utils.Utils
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    var viewPager: ViewPager? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var img_drawer: ImageView
    var mSlideState = false;
    lateinit var switchtogglebutton: SwitchCompat
    lateinit var navigationview: NavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        listners(view)


        val pref: SharedPreferences = requireActivity().getSharedPreferences("save", MODE_PRIVATE)
        switchtogglebutton.setChecked(pref.getBoolean("first", false))



        drawerLayout = view.findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout, R.string.nav_open, R.string.nav_close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
//        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val tabLayout = view.findViewById(com.app.photomaker.R.id.tabLayout) as TabLayout
        viewPager = view.findViewById(R.id.viewPager) as ViewPager

        tabLayout.addTab(tabLayout.newTab().setText("Stories"));
        tabLayout.addTab(tabLayout.newTab().setText("Post"));


        var adapter = TabslayoutsAdapter(childFragmentManager)
        adapter.addFragment(StoriesFragment(), "Stories")
        adapter.addFragment(StoriesFragment(), "Posts")

        // Adding the Adapter to the ViewPager
        viewPager?.adapter = adapter
        switchbuttononoff()

        // bind the viewPager with the TabLayout.
        tabLayout.setupWithViewPager(viewPager)


        navigationview.setNavigationItemSelectedListener (this)

        return view
    }

    private fun init(view: View?) {
        viewPager = view?.findViewById(R.id.viewPager)
        img_drawer = view?.findViewById(R.id.img_drawer)!!
        switchtogglebutton = view?.findViewById(R.id.switchtogglebutton)!!
        navigationview = view?.findViewById(R.id.navigationview)!!

    }

    private fun listners(view: View?) {
        img_drawer.setOnClickListener(this)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }


    override fun onClick(p0: View?) {
        if (p0 == img_drawer) {
            clickEventSlide()
        }
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
                val editor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit()
                editor.putBoolean("first", true)
                editor.apply()
                switchtogglebutton.isChecked = true
                Utils.showDialogMethod(activity, activity?.fragmentManager)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Utils.hideDialog()
 },
                    2000)

            } else {
                val editor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit()
                editor.putBoolean("first", false)
                editor.apply()
                switchtogglebutton.setChecked(false)
                CSPreferences.putString(requireActivity(), Utils.ROLE, "false")
                Utils.showDialogMethod(activity, activity?.fragmentManager)
                Handler(Looper.getMainLooper()).postDelayed({
                    Utils.hideDialog()

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }, 2000)

            }

        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tv_dismiss -> {
                Toast.makeText(activity, "sddddddddddddddddddddddddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show()
                drawerLayout.close()
            }
        }
        return false
    }


}