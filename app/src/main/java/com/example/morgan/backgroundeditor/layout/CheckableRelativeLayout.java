package com.example.morgan.backgroundeditor.layout;

import android.content.Context;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
    private boolean isChecked;

    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        // set background here? might be overriden
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }


}
