package com.example.morgan.backgroundeditor.custom;

public class ImageModel {
    private String imageLocation;
    private boolean isSelected = false;

    public ImageModel(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageView() {
        return imageLocation;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
