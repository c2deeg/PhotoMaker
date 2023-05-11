package com.app.photomaker.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Activities.CreatePostActivity.CreatePostActivity
import com.app.photomaker.R

class ClassicoAnimateRecyclerdapter(private val activity: FragmentActivity) :RecyclerView.Adapter<ClassicoAnimateRecyclerdapter.ViewHolder>() {



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var cardview = itemView.findViewById<CardView>(R.id.cardview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(activity).inflate(R.layout.classicoanimateditem,parent,false)
        return ClassicoAnimateRecyclerdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardview.setOnClickListener{
            var intent = Intent(activity,CreatePostActivity::class.java)
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return  10
    }
}