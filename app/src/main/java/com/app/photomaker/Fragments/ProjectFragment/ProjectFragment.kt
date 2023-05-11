package com.app.photomaker.Fragments.ProjectFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Activities.PlanMemberShipActivity
import com.app.photomaker.Adapter.ProjectGridRecylerAdapter
import com.app.photomaker.R
import java.io.File


class ProjectFragment : Fragment(), View.OnClickListener {
    lateinit var txt_tryplus: TextView
    lateinit var grid_ecylerview: RecyclerView
    private var projectGridRecylerAdapter:ProjectGridRecylerAdapter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_project, container, false)
        init(view)
        listners(view)

//        projectGridRecylerAdapter = ProjectGridRecylerAdapter(activity as FragmentActivity)
//        val layoutManager = GridLayoutManager(activity, 2)
//
//        grid_ecylerview.layoutManager = layoutManager
//        grid_ecylerview.adapter = projectGridRecylerAdapter

        var path = "/storage/emulated/0/Pictures/PhotoMaker/"

        val file = File(path )


        return view
    }

    private fun init(view: View) {
        txt_tryplus = view?.findViewById(R.id.txt_tryplus)!!
        grid_ecylerview = view?.findViewById(R.id.grid_ecylerview)!!

    }

    private fun listners(view: View) {
        txt_tryplus.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == txt_tryplus) {

            var intent = Intent(activity, PlanMemberShipActivity::class.java)
            startActivity(intent)

        }
    }

}