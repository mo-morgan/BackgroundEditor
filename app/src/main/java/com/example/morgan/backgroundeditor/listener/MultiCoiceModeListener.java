package com.example.morgan.backgroundeditor.listener;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MultiCoiceModeListener implements GridView.MultiChoiceModeListener {

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
