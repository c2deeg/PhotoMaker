package com.app.photomaker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.photomaker.R
import com.app.photomaker.SplashActivity3
import com.bumptech.glide.Glide

class GridRecyclerAdapter(private val activity: SplashActivity3) :
    RecyclerView.Adapter<GridRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.griditems, parent, false)
        return GridRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridRecyclerAdapter.ViewHolder, position: Int) {

        if (position == 0) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react1)).into(holder.image);
        } else if (position == 1) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react2)).into(holder.image);
        } else if (position == 2) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react3)).into(holder.image);
        } else if (position == 3) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react4)).into(holder.image);
        } else if (position == 4) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react1)).into(holder.image);
        } else if (position == 5) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react2)).into(holder.image);
        } else if (position == 6) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react3)).into(holder.image);
        } else if (position == 7) {
            Glide.with(activity).load(activity.getDrawable(R.drawable.react4)).into(holder.image);
        }
    }

    override fun getItemCount(): Int {
        return 8
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var image: ImageView = itemView.findViewById(R.id.img_griditem)


    }
}