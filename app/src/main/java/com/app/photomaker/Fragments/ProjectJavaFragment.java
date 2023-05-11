package com.app.photomaker.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.photomaker.Activities.PlanMemberShipActivity;
import com.app.photomaker.Adapter.CourseGVAdapter;
import com.app.photomaker.Adapter.ProjectGridRecylerAdapter;
import com.app.photomaker.Interfaces.OnItemClickListener;
import com.app.photomaker.R;
import com.app.photomaker.StaticModel.Photo;
import com.app.photomaker.Utils.SpacesItemDecoration;
import com.app.photomaker.Utils.Utils;

import java.io.File;
import java.util.ArrayList;


public class ProjectJavaFragment extends Fragment implements OnItemClickListener, View.OnClickListener {
    private View  view;
    private GridView idGVcourses;
    private ProjectGridRecylerAdapter projectGridRecylerAdapter;
    private ArrayList<Photo>arrayList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private CourseGVAdapter adapter;
    private TextView txt_tryplus;
    private TextView tv_noproject;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_project_java, container, false);
        init();
        listners();
        requestRead();


         adapter = new CourseGVAdapter(getActivity(), arrayList,this);
        idGVcourses.setAdapter(adapter);
        if (arrayList.size()==0){
            idGVcourses.setVisibility(View.GONE);
            tv_noproject.setVisibility(View.VISIBLE);
        }else {
            tv_noproject.setVisibility(View.GONE);
            idGVcourses.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void requestRead() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            getImages();
        }
    }

    private void init(){
        idGVcourses= view.findViewById(R.id.idGVcourses);
        txt_tryplus= view.findViewById(R.id.txt_tryplus);
        tv_noproject= view.findViewById(R.id.tv_noproject);

    }
    private void listners(){
        txt_tryplus.setOnClickListener(this);

    }
    private void getImages(){
        arrayList.clear();
        String filePath = "/storage/emulated/0/Pictures/PhotoMaker/";
        File file = new File(filePath);
        File [] files = file.listFiles();
        if (files!=null){
            for (File file1 : files){

                if (file1.getPath().endsWith(".png")||file1.getPath().endsWith(".jpg")){
                    arrayList.add(new Photo(file1.getName(),file1.getPath(),file1.length()));
                    Log.d("chchchch","local");
                    Log.d("chchchch",arrayList.size()+"");
                    Log.d("chchchch",arrayList.get(0).getPath()+"");
                }

            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ArrayList<Photo> arraylist, int position, ImageView img_menu) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), img_menu);
        this.arrayList = arraylist;

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Toast message on menu item clicked
                Toast.makeText(getActivity(), "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                File file = new File(arraylist.get(position).getPath());
                file.delete();
                adapter.notifyDataSetChanged();
                Utils.Companion.homesActivitychangeFragment(getActivity(),new ProjectJavaFragment());




                Toast.makeText(getActivity(),arraylist.size()+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),"Image Deleted Sucessfully",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();

    }

    @Override
    public void onClick(View view) {
        if (view==txt_tryplus){
            Intent intent = new Intent(getActivity(), PlanMemberShipActivity.class);
            startActivity(intent);
        }
    }
}