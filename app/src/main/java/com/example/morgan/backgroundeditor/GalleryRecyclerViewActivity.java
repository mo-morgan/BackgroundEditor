package com.example.morgan.backgroundeditor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import com.example.morgan.backgroundeditor.adapter.ImageAdapter;

public class GalleryRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_recycler_view);

        recyclerView = findViewById(R.id.imageGallery);

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        imageAdapter = new ImageAdapter(this);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLongClickable(true);
    }
}
