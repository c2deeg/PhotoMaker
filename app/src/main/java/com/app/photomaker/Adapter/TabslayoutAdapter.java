package com.app.photomaker.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.photomaker.Fragments.PostFragment.PostFragment;
import com.app.photomaker.Fragments.StoriesFragment.StoriesFragment;

public class TabslayoutAdapter extends FragmentPagerAdapter {

    public TabslayoutAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StoriesFragment homeFragment = new StoriesFragment();
                return homeFragment;
            case 1:
                PostFragment sportFragment = new PostFragment();
                return sportFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Stories";
        else if (position == 1)
            title = "Posts";
        return title;
    }
}
