package com.app.photomaker.Activities.FilterSetImageActivity4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.photomaker.Adapter.RecyclerAdapter;
import com.app.photomaker.R;

import java.util.ArrayList;

public class FilterSetImageActivity4 extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    TextView tv_selcimages;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Uri>uri = new ArrayList<>();
    private static final int  Read_permission = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_set_image4);
        recyclerView = findViewById(R.id.recyclerview_Gallery);
        tv_selcimages = findViewById(R.id.tv_selcimages);
        tv_selcimages.setOnClickListener(this);
        recyclerAdapter = new RecyclerAdapter(uri);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Read_permission);
        }
    }

    @Override
    public void onClick(View view) {
        if (view==tv_selcimages){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode== Activity.RESULT_OK){
            if (data.getClipData()!=null){
                int x = data.getClipData().getItemCount();
                for (int i = 0;i<x;i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());

                }
                recyclerAdapter.notifyDataSetChanged();
                tv_selcimages.setText("Photos("+uri.size()+")");
            }else if (data.getData() != null){
                String imageURL = data.getData().getPath();
                uri.add(Uri.parse(imageURL));
            }
        }
    }
}