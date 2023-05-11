package com.app.photomaker.Fragments.StoriesFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Adapter.ClassicoAnimateRecyclerdapter
import com.app.photomaker.Adapter.ForyouRecyclerAdapter
import com.app.photomaker.Adapter.StoriesFilterRecyclerAdapter
import com.app.photomaker.Interfaces.StoriesAdapterClickListner
import com.app.photomaker.R
import com.app.photomaker.StaticModel.ForyouModel
import com.app.photomaker.StaticModel.StoriesModel
import com.app.photomaker.Utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.collections.ArrayList

class StoriesFragment : Fragment(), StoriesAdapterClickListner {
    var Storiesfilter_recyclerview: RecyclerView? = null
    var foryou_recyclerview: RecyclerView? = null
    var animated_recyclerview: RecyclerView? = null
    var storiesFilterRecyclerAdapter: StoriesFilterRecyclerAdapter? = null
    var foryouRecyclerAdapter: ForyouRecyclerAdapter? = null
    var classicoRecyclerAdapter: ClassicoAnimateRecyclerdapter? = null
    var activity: Activity? = null
    var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_stories, container, false)
        activity = getActivity()
        init(view)
        listeners(view)
        var arrayList: ArrayList<StoriesModel> = ArrayList()
        arrayList.add(StoriesModel(R.drawable.ocean,"Classico"))
        arrayList.add(StoriesModel(R.drawable.makeupo,"Wedding"))
        arrayList.add(StoriesModel(R.drawable.cake,"Birthday"))
        arrayList.add(StoriesModel(R.drawable.ocean,"Anin"))
        arrayList.add(StoriesModel(R.drawable.cake,"Promotion"))
        arrayList.add(StoriesModel(R.drawable.makeupo,"Birthday"))
        storiesFilterRecyclerAdapter =
            StoriesFilterRecyclerAdapter(activity as FragmentActivity, this,arrayList)
        Storiesfilter_recyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        Storiesfilter_recyclerview?.adapter = storiesFilterRecyclerAdapter


        var arrayList2: ArrayList<ForyouModel> = ArrayList()
        arrayList2.add(ForyouModel(R.drawable.frame2))
        arrayList2.add(ForyouModel(R.drawable.imgfiannl))
        arrayList2.add(ForyouModel(R.drawable.frame1))
        arrayList2.add(ForyouModel(R.drawable.frame2))
        arrayList2.add(ForyouModel(R.drawable.imgfiannl))

        foryouRecyclerAdapter = ForyouRecyclerAdapter(activity as FragmentActivity,arrayList2)
        foryou_recyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        foryou_recyclerview?.adapter = foryouRecyclerAdapter

        classicoRecyclerAdapter = ClassicoAnimateRecyclerdapter(activity as FragmentActivity)

        animated_recyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        animated_recyclerview?.adapter = classicoRecyclerAdapter


        return view
    }

    private fun init(view: View?) {
        Storiesfilter_recyclerview = view?.findViewById(R.id.Storiesfilter_recyclerview)
        foryou_recyclerview = view?.findViewById(R.id.foryou_recycleradapter)
        animated_recyclerview = view?.findViewById(R.id.animated_recyclerview)

    }

    private fun listeners(view: View?) {

    }

    override fun onItemClick(position: Int) {
//        if (position==0){
        Utils.showDialogMethod(activity, activity?.fragmentManager)
        foryou_recyclerview?.visibility = View.INVISIBLE



        Handler(Looper.getMainLooper()).postDelayed({
            Utils.hideDialog()
            foryou_recyclerview?.visibility = View.VISIBLE


        }, 2000)


    }










}