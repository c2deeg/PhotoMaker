package com.app.photomaker.StaticModel;

import com.app.photomaker.R;

public enum ModelObject {

    RED(R.string.red, R.layout.viewpager1),
    BLUE(R.string.red, R.layout.viewpager1),
    GREEN(R.string.red, R.layout.viewpager1);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
