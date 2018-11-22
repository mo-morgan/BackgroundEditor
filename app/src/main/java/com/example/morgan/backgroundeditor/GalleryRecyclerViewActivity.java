package com.example.morgan.backgroundeditor;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.morgan.backgroundeditor.adapter.ImageAdapter;
import com.example.morgan.backgroundeditor.custom.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static ImageAdapter imageAdapter;
    private LayoutManager layoutManager;

    private static FloatingActionButton cancel;
    private static FloatingActionButton submit;
    private Dialog dialog;
    private String folderName;

    private static final String DIALOG_TITLE = "Enter your desired folder name: ";

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

        cancel = (FloatingActionButton) this.findViewById(R.id.cancel);
        submit = (FloatingActionButton) this.findViewById(R.id.submit);

        if (imageAdapter.getSelectedSize() == 0) {
            cancel.hide();
            submit.hide();
        }

        setupFloatingButtons();
        dialog = new Dialog(GalleryRecyclerViewActivity.this);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.setTitle(R.string.app_name);
    }

    private void setupFloatingButtons() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ImageModel> k = new ArrayList<>();
                for (ImageModel i : imageAdapter.getSelectedImages()) {
                    i.getImage().setColorFilter(null);
                    k.add(i);
                }
                imageAdapter.getSelectedImages().removeAll(k);
                cancel.hide();
                submit.hide();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // should send a form to making a new folder name
//                alertDialog = new AlertDialog.Builder(GalleryRecyclerViewActivity.this);
//                alertDialog.setTitle(DIALOG_TITLE);
//                final EditText input = new EditText(GalleryRecyclerViewActivity.this);
//                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (input.getText().toString().isEmpty()) {
//                            Animation shake = AnimationUtils.loadAnimation(GalleryRecyclerViewActivity.this, R.anim.shake);
//                            input.startAnimation(shake);
//                            Toast.makeText(GalleryRecyclerViewActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
//                        } else {
//                            folderName = input.getText().toString();
//                        }
//                    }
//                });
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                alertDialog.setView(input);
//                alertDialog.show();
                final EditText editText = (EditText) dialog.findViewById(R.id.folder_edit_text);

                Button positive = (Button) dialog.findViewById(R.id.OkButton);
                Button negative = (Button) dialog.findViewById(R.id.CancelButton);

                dialog.show();

                positive.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().isEmpty()) {
                            Animation shake = AnimationUtils.loadAnimation(GalleryRecyclerViewActivity.this, R.anim.shake);
                            editText.startAnimation(shake);
                            Toast.makeText(GalleryRecyclerViewActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                        } else {
                            folderName = editText.getText().toString();
                        }
                    }
                });

                negative.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public static void setupSubmitButton() {
        if (imageAdapter.getSelectedSize() == 0) {
            submit.hide();
            return;
        }

        submit.show();
    }

    /**
     * Setup cancel button: on click should remove all highlights
     * from selected images as well as removing them from list
     */
    public static void setupCancelButton() {
        if (imageAdapter.getSelectedSize() == 0) {
            // always set to invisible
            cancel.hide();
            return;
        }
        // set button to be visible
        cancel.show();
    }
}
