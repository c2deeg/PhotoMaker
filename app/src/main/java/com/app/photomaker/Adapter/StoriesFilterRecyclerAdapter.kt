package com.app.photomaker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Interfaces.StoriesAdapterClickListner
import com.app.photomaker.R
import com.app.photomaker.StaticModel.StoriesModel

class StoriesFilterRecyclerAdapter(
    private val activity: FragmentActivity,
    private val clickinterface: StoriesAdapterClickListner,
   private val arrayList: ArrayList<StoriesModel>
) :
    RecyclerView.Adapter<StoriesFilterRecyclerAdapter.ViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(activity).inflate(R.layout.storiesfilterrecyclerviewitem,parent,false)
        return StoriesFilterRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img_filter.setImageResource(arrayList.get(position).image!!)
        holder.tv_filtertitle.text =arrayList.get(position).texttittle
        holder.img_filter.setOnClickListener{
            clickinterface.onItemClick(position)

        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var img_filter: ImageView = itemView.findViewById(R.id.img_filter)
        var tv_filtertitle: TextView = itemView.findViewById(R.id.tv_filtertitle)

    }
}