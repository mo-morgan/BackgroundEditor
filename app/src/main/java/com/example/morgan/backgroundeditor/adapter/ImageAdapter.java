package com.example.morgan.backgroundeditor.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.morgan.backgroundeditor.FullScreenViewActivity;
import com.example.morgan.backgroundeditor.R;
import com.example.morgan.backgroundeditor.custom.ImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom ImageAdapter Class for RecyclerView gallery
 */

// TODO: neeed to refactor class such that ImageModel stores ImageView, Location, and whether it is selected
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Activity context;

    private RelativeLayout relativeLayout;
    private FloatingActionButton cancel;
    private FloatingActionButton submit;

    private List<ImageModel> images = new ArrayList<>();
    private List<ImageModel> selectedImages = new ArrayList<>();


    public ImageAdapter(Activity localContext) {
        context = localContext;
        for (String s : getAllShownImagesPath(context)) {
            images.add(new ImageModel(s));
        }
        relativeLayout = context.findViewById(R.id.galleryMain);

        cancel = (FloatingActionButton) context.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ImageModel i : selectedImages) {
                    i.getImage().setColorFilter(null);
                    selectedImages.remove(i);
                }
            }
        });
//        setupCancelButton();
//        setupSubmitButton();
    }

    //TODO: Setup Floating action button on top of recycler view
    private void setupSubmitButton() {

    }

    /**
     * Setup cancel button: on click should remove all highlights
     * from selected images as well as removing them from list
     */
    private void setupCancelButton() {
        if (selectedImages.size() == 0) {
            // always set to invisible
            return;
        }

        // set button to be visible
    }

    public Object getItem(int position) {
        return images.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ImageModel model = images.get(position);
        if (selectedImages.contains(model)) {
            viewHolder.imageView.setColorFilter(ContextCompat.getColor(context, R.color.cyan), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            viewHolder.imageView.setColorFilter(null);
        }
        model.setImageView(viewHolder.imageView);

        Glide.with(context)
                .load(model.getImageView())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.makise)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into((ImageView) viewHolder.itemView);

        viewHolder.itemView.setOnClickListener(new OnImageClickListener(position, model, viewHolder));
        viewHolder.itemView.setOnLongClickListener(new OnImageLongClickListener(model, viewHolder));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }



    /**
     * @param activity
     * @return          ArrayList with images path
     */
    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int colum_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        };

        cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        colum_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(colum_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }

    /**
     * Overriden OnClickListener
     *  - adds/removes image to/from selected images if at least 1 item is selected
     *  - otherwise, goes to fullscreen activity
     */
    public class OnImageClickListener implements View.OnClickListener {
        public int position;
        public ImageModel model;
        public ViewHolder holder;

        public OnImageClickListener(int position, ImageModel model, ViewHolder holder) {
            this.model = model;
            this.position = position;
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            if (selectedImages.size() > 0) {
                if (selectedImages.contains(model)) {
                    selectedImages.remove(model);
                    holder.imageView.setColorFilter(null);
                    setupCancelButton();
                    setupSubmitButton();
                } else {
                    selectedImages.add(model);
                    holder.imageView.setColorFilter(ContextCompat.getColor(context, R.color.cyan), android.graphics.PorterDuff.Mode.MULTIPLY);
                }
                model.setSelected(!model.isSelected());

            } else {
                Intent i = new Intent(context, FullScreenViewActivity.class);
                i.putExtra("position", position);
                context.startActivity(i);
            }
        }
    }

    /**
     * Overriden OnLongClickListener
     * - adds/removes image to/from selected images
     */
    public class OnImageLongClickListener implements View.OnLongClickListener {
        public ImageModel model;
        public ViewHolder holder;


        public OnImageLongClickListener(ImageModel model, ViewHolder holder) {
            this.model = model;
            this.holder = holder;
        }

        @Override
        public boolean onLongClick(View v) {
            if (selectedImages.contains(model)) {
                selectedImages.remove(model);
                holder.imageView.setColorFilter(null);
            } else {
                selectedImages.add(model);
                holder.imageView.setColorFilter(ContextCompat.getColor(context, R.color.cyan), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
            model.setSelected(!model.isSelected());
            return true;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}


