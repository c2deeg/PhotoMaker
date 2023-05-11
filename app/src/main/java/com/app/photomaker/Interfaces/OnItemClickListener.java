package com.app.photomaker.Interfaces;

import android.widget.ImageView;

import com.app.photomaker.StaticModel.Photo;

import java.util.ArrayList;

public interface OnItemClickListener {


    void onItemClick(ArrayList<Photo> arraylist, int position, ImageView img_menu);
}
