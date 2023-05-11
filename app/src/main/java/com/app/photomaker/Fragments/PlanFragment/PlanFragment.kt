package com.app.photomaker.Fragments.PlanFragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.app.humanresource.Utils.CSPreferences
import com.app.photomaker.Adapter.ImageAdapter
import com.app.photomaker.Adapter.PlanViewPagerAdapter
import com.app.photomaker.R
import com.app.photomaker.Utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import me.relex.circleindicator.CircleIndicator


class PlanFragment : Fragment(), View.OnClickListener {

    lateinit var txt_tryplus: TextView
    lateinit var viewPage: ViewPager
    lateinit var indicator: CircleIndicator
    lateinit var adapter: ImageAdapter
    lateinit var btn_createfreelink: Button
    var bottomSheetDialog: BottomSheetDialog? = null
    var tv_notsure: TextView? = null
    var linear1: LinearLayout? = null
    var linear2: LinearLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_plan, container, false)

        // Inflate the layout for this fragment
        init(view)
        listners(view)

        val mViewPager = view.findViewById(com.app.photomaker.R.id.viewPage) as ViewPager


        val imageAdapter = PlanViewPagerAdapter(activity)

        // add the fragments

        // add the fragments


        // Set the adapter

        // Set the adapter
        mViewPager.setAdapter(imageAdapter)
//        var adapterView = ImageAdapter(activity as FragmentActivity,arrayList)
//        mViewPager.adapter = adapterView
        indicator.setViewPager(mViewPager)



        return view
    }

    private fun init(view: View) {
        viewPage = view.findViewById(com.app.photomaker.R.id.viewPage)
        indicator = view.findViewById(com.app.photomaker.R.id.indicator)
        btn_createfreelink = view.findViewById(com.app.photomaker.R.id.btn_createfreelink)
        tv_notsure = view.findViewById(com.app.photomaker.R.id.tv_notsure)

    }

    private fun listners(view: View) {
        btn_createfreelink.setOnClickListener(this)
        tv_notsure?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == btn_createfreelink) {
            showBottomSheetDialog()
        }else if (p0==tv_notsure){
            bottomSheetDialog?.dismiss()

        }else if (p0==linear2){
            instagram()

        }else if(p0==tv_notsure){
            bottomSheetDialog?.dismiss()
        }else if (p0==linear1){
            instagram();
        }

    }

    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.SheetDialog)
        bottomSheetDialog?.setContentView(R.layout.connectfreelink)
        bottomSheetDialog?.show()
        linear2 = bottomSheetDialog?.findViewById<LinearLayout>(R.id.linear2)
        linear1 = bottomSheetDialog?.findViewById<LinearLayout>(R.id.linear1)
        tv_notsure = bottomSheetDialog?.findViewById<TextView>(R.id.tv_notsure)
//        tv_capture = bottomSheetDialog?.findViewById<TextView>(R.id.tv_capture)
//        tv_createpost = bottomSheetDialog?.findViewById<TextView>(R.id.tv_createpost)
//
        linear2?.setOnClickListener(this)
        linear1?.setOnClickListener(this)
        tv_notsure?.setOnClickListener(this)
//        tv_capture?.setOnClickListener(this)
//        tv_createpost?.setOnClickListener(this)

    }

    private fun instagram(){
        val uri: Uri = Uri.parse("http://instagram.com/_u/YOUR_USERNAME")


        val i = Intent(Intent.ACTION_VIEW, uri)

        i.setPackage("com.instagram.android")

        try {
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/xxx")
                )
            )
        }
    }

}