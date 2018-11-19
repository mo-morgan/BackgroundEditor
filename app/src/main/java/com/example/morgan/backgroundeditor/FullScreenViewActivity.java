package com.example.morgan.backgroundeditor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.morgan.backgroundeditor.Utils.Utils;
import com.example.morgan.backgroundeditor.adapter.FullScreenImageAdapter;

public class FullScreenViewActivity extends AppCompatActivity {
    private FullScreenImageAdapter adapter;
    private ViewPager pager;
    private Utils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);
        utils = new Utils(getApplicationContext());

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);

        pager = findViewById(R.id.pager);
        adapter = new FullScreenImageAdapter(this, utils.getAllShownImagesPath(this));

        pager.setAdapter(adapter);

        pager.setCurrentItem(position);

        // to hide status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        pager.setSystemUiVisibility(uiOptions);
    }
}
