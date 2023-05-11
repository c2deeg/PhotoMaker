package com.app.photomaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.photomaker.Activities.ViewImageInFullScreen.ViewImageInFullScreen;
import com.app.photomaker.Interfaces.OnItemClickListener;
import com.app.photomaker.R;
import com.app.photomaker.StaticModel.Photo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CourseGVAdapter extends ArrayAdapter<Photo> {

    private final Context activty;
    private final ArrayList<Photo> arraylist;
    private final OnItemClickListener onItemClickListener;
    private CourseGVAdapter courseGVAdapter;
    public CourseGVAdapter(@NonNull Context context, ArrayList<Photo> courseModelArrayList, OnItemClickListener onItemClickListener) {
        super(context, 0, courseModelArrayList);
        this.activty = context;
        this.arraylist = courseModelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.projectgriditem, parent, false);
        }

        Photo courseModel = getItem(position);
        ImageView courseIV = listitemView.findViewById(R.id.img_projectrgrid);
        ImageView img_menu = listitemView.findViewById(R.id.img_menu);
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(arraylist,position,img_menu);
                notifyDataSetChanged();

            }
        });
        courseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activty, ViewImageInFullScreen.class);
                intent.putExtra("path",arraylist.get(position).getPath());
                activty.startActivity(intent);

            }
        });

        Glide.with(activty).load(courseModel.getPath()).into(courseIV);

        return listitemView;
    }
}
