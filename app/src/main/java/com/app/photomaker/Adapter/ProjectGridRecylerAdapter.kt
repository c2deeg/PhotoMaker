package com.app.photomaker.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Activities.ViewImageInFullScreen.ViewImageInFullScreen
import com.app.photomaker.R
import com.app.photomaker.StaticModel.Photo
import com.bumptech.glide.Glide
import java.util.ArrayList

class ProjectGridRecylerAdapter(
    private val activity: FragmentActivity,
    private val arrayList: ArrayList<Photo>
) :RecyclerView.Adapter<ProjectGridRecylerAdapter.ViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(activity).inflate(R.layout.projectgriditem,parent,false)
        return ProjectGridRecylerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(activity).load(arrayList.get(position).path).into(holder.img_projectrgrid);

        holder.img_projectrgrid.setOnClickListener{
            var intent = Intent(activity,
                ViewImageInFullScreen::class.java)
            intent.putExtra("path",arrayList.get(position).path)
            activity.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return arrayList.size

    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var img_projectrgrid: ImageView = itemView.findViewById(R.id.img_projectrgrid)

    }
}