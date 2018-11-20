package com.example.morgan.backgroundeditor.custom;

import android.widget.ImageView;

public class ImageModel {
    private String imageLocation;
    private ImageView imageview;
    private boolean isSelected = false;

    public ImageModel(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageView() {
        return imageLocation;
    }

    public ImageView getImage() { return imageview;}

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setImageView(ImageView imageview) { this.imageview = imageview; }
}
