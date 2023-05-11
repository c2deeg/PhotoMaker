package com.app.photomaker.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.Activities.FilterSetImageActivity.FilterSetImageActivity
import com.app.photomaker.Activities.FilterSetImageActivity2.FilterSetImageActivity2
import com.app.photomaker.Activities.FilterSetImageActivity3.FilterSetImageActivity3
import com.app.photomaker.Activities.FilterSetImageActivity4.FilterSetImageActivity4
import com.app.photomaker.R
import com.app.photomaker.StaticModel.ForyouModel
import com.app.photomaker.Utils.Utils

class ForyouRecyclerAdapter(
    private val activity: FragmentActivity,
    private val arrayList2: ArrayList<ForyouModel>
) : RecyclerView.Adapter<ForyouRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.foryourecyleritem, parent, false)
        return ForyouRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img_foryou.setImageResource(arrayList2.get(position).img!!)

        holder.img_foryou.setOnClickListener {

            if (position == 0) {
                var intent = Intent(activity, FilterSetImageActivity::class.java)
                activity.startActivity(intent)
            } else if (position == 1) {
                var intent = Intent(activity, FilterSetImageActivity2::class.java)
                intent.putExtra("val","values")
                activity.startActivity(intent)
            } else if (position == 2) {
                var intent = Intent(activity, FilterSetImageActivity3::class.java)
                intent.putExtra("val","values")
                activity.startActivity(intent)
            }else if(position==3){
                var intent = Intent(activity, FilterSetImageActivity3::class.java)
                intent.putExtra("val","value")
                activity.startActivity(intent)
            }else if(position==4){
                var intent = Intent(activity, FilterSetImageActivity2::class.java)
                intent.putExtra("val","value")
                activity.startActivity(intent)
            }

        }



        if (position == 0 || position == 2) {
            holder.img_lock.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        return arrayList2.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var img_foryou: ImageView = itemView.findViewById(R.id.img_foryou)
        var img_lock: ImageView = itemView.findViewById(R.id.img_lock)

    }
}