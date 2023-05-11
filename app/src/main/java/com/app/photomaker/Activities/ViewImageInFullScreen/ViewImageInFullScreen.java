package com.app.photomaker.Activities.ViewImageInFullScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.photomaker.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ViewImageInFullScreen extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_fullimage;
    private ImageView img_share;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image_in_full_screen);
        img_fullimage= findViewById(R.id.img_fullimage);
         path = getIntent().getStringExtra("path");
        Glide.with(this).load(path).into(img_fullimage);

        init();
        listeners();

    }
    private void init(){
        img_share  = findViewById(R.id.img_share);
    }

    private void listeners() {
        img_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==img_share) {
            image();
        }


    }

    private void image(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        BitmapDrawable drawable = (BitmapDrawable) img_fullimage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File f = new File(path);
        String username = "pranjal_pvt_43";
         String hackversion = username+"";
        Intent shareint;

         try {
             FileOutputStream outputStream = new FileOutputStream(f);
             bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
             outputStream.flush();
             outputStream.close();
             shareint = new Intent(Intent.ACTION_SEND);
             shareint.setType("image/*");
             shareint.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
             shareint.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

         }catch (Exception e){
             throw new RuntimeException(e);
         }
         startActivity(Intent.createChooser(shareint,"share"));

    }



}