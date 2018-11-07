package com.example.morgan.backgroundeditor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.provider.MediaStore.MediaColumns;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private ArrayList<ImageModel> images = new ArrayList<>();
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        final GridView gallery = (GridView) findViewById(R.id.gallery_grid_view);

        adapter = new ImageAdapter(this);
        gallery.setAdapter(adapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                if (null != images && !images.isEmpty()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "position " + position + " " + images.get(position),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GalleryActivity.this, FullScreenViewActivity.class);

                    startActivity(intent);
                }
            }


        });

        gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != images && !images.isEmpty()) {
                    ImageModel image = images.get(position);
                    if (image.isSelected) {
                        image.isSelected = false;
                    } else {
                        image.isSelected = true;
                    }
//                    gallery.setItemChecked(position, true);
//                    adapter.addSelectedPosition(position);
                    adapter.setMultiselect(true);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {

        private Activity context;

        private boolean multiselect;
//        private ImageView picturesView;

        private List<Integer> selectedPositions = new ArrayList<>();


        public ImageAdapter(Activity localContext) {
            context = localContext;
            for (String s : getAllShownImagesPath(context)) {
                images.add(new ImageModel(s, false));
            }
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setMultiselect(boolean value) {
            multiselect = value;
        }

        public void addSelectedPosition(int position) {
            selectedPositions.add(position);
        }

        public void removeSelectedPosition(int position) {
            if (selectedPositions.contains(position))
                selectedPositions.remove(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                images.get(position).pictureView = new ImageView(context);
                images.get(position).pictureView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                images.get(position).pictureView = (ImageView) convertView;
            }

            Glide.with(context)
                    .load(images.get(position).url)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.makise)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(images.get(position).pictureView);

//            if (selectedPositions.contains(position)) {
//                picturesView.setColorFilter(ContextCompat.getColor(context, R.color.green_main_color), android.graphics.PorterDuff.Mode.MULTIPLY);
//                Log.d("POSITION IS: ", Integer.toString(position)); // pos is correct, but picturesView is getting recycled
//            }

            ImageModel image = images.get(position);
            if (image.isSelected) { // show highlight image view
                images.get(position).pictureView
                        .setColorFilter(ContextCompat.getColor(context, R.color.green_main_color),
                                android.graphics.PorterDuff.Mode.MULTIPLY);
            } else {

            }

            return images.get(position).pictureView;
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
                    MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME
            };

            cursor = activity.getContentResolver().query(uri, projection, null, null, null);
            colum_index_data = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(colum_index_data);

                listOfAllImages.add(absolutePathOfImage);
            }
            return listOfAllImages;
        }
    }

    public class ImageModel {
        public String url;
        public boolean isSelected;
        public ImageView pictureView;

        public ImageModel(String url, boolean isSelected) {
            this.url = url;
            this.isSelected = isSelected;
        }
    }

    //
//    private ArrayList<ImageModel> images = new ArrayList<>();
//    private ImageAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gallery);
//
//        final GridView gallery = (GridView) findViewById(R.id.gallery_grid_view);
//
//        adapter = new ImageAdapter(this);
//        gallery.setAdapter(adapter);
//        gallery.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
//        gallery.setMultiChoiceModeListener(new MultiCoiceModeListener());
//
//        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int position, long arg3) {
//                if (null != images && !images.isEmpty()) {
//                    Toast.makeText(
//                            getApplicationContext(),
//                            "position " + position + " " + images.get(position),
//                            Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(GalleryActivity.this, FullScreenViewActivity.class);
//
//                    startActivity(intent);
//                }
//            }
//
//
//        });
//
//        gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (null != images && !images.isEmpty()) {
//                    ImageModel image = images.get(position);
//                    if (image.isSelected) {
//                        image.isSelected = false;
//                    } else {
//                        image.isSelected = true;
//                    }
////                    gallery.setItemChecked(position, true);
////                    adapter.addSelectedPosition(position);
//                    adapter.setMultiselect(true);
//                    adapter.notifyDataSetChanged();
//                }
//                return true;
//            }
//        });
//    }
//
//    private class ImageAdapter extends BaseAdapter {
//
//        private Activity context;
//
//        private boolean multiselect;
////        private ImageView picturesView;
//
//        private List<Integer> selectedPositions = new ArrayList<>();
//
//
//        public ImageAdapter(Activity localContext) {
//            context = localContext;
//            for (String s : getAllShownImagesPath(context)) {
//                images.add(new ImageModel(s, false));
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return images.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return images.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public void setMultiselect(boolean value) {
//            multiselect = value;
//        }
//
//        public void addSelectedPosition(int position) {
//            selectedPositions.add(position);
//        }
//
//        public void removeSelectedPosition(int position) {
//            if (selectedPositions.contains(position))
//                selectedPositions.remove(position);
//        }
//
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                images.get(position).pictureView = new ImageView(context);
//                images.get(position).pictureView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            } else {
//                images.get(position).pictureView = (ImageView) convertView;
//            }
//
//            Glide.with(context)
//                    .load(images.get(position).url)
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.makise)
//                            .centerCrop()
//                            .diskCacheStrategy(DiskCacheStrategy.ALL))
//                    .into(images.get(position).pictureView);
//
////            if (selectedPositions.contains(position)) {
////                picturesView.setColorFilter(ContextCompat.getColor(context, R.color.green_main_color), android.graphics.PorterDuff.Mode.MULTIPLY);
////                Log.d("POSITION IS: ", Integer.toString(position)); // pos is correct, but picturesView is getting recycled
////            }
//
//            ImageModel image = images.get(position);
//            if (image.isSelected) { // show highlight image view
//                images.get(position).pictureView
//                        .setColorFilter(ContextCompat.getColor(context, R.color.green_main_color),
//                                android.graphics.PorterDuff.Mode.MULTIPLY);
//            } else {
//
//            }
//
//            return images.get(position).pictureView;
//        }
//
//        /**
//         * @param activity
//         * @return          ArrayList with images path
//         */
//        private ArrayList<String> getAllShownImagesPath(Activity activity) {
//            Uri uri;
//            Cursor cursor;
//            int colum_index_data, column_index_folder_name;
//            ArrayList<String> listOfAllImages = new ArrayList<>();
//            String absolutePathOfImage = null;
//            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//            String[] projection = {
//                    MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME
//            };
//
//            cursor = activity.getContentResolver().query(uri, projection, null, null, null);
//            colum_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//            while (cursor.moveToNext()) {
//                absolutePathOfImage = cursor.getString(colum_index_data);
//
//                listOfAllImages.add(absolutePathOfImage);
//            }
//            return listOfAllImages;
//        }
//    }
//
//    public class ImageModel {
//        public String url;
//        public boolean isSelected;
//        public ImageView pictureView;
//
//        public ImageModel(String url, boolean isSelected) {
//            this.url = url;
//            this.isSelected = isSelected;
//        }
//    }
}
