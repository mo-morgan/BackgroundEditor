package model.ui.util;

import java.util.List;
import java.util.Objects;

/**
 * A PNG class utilizing
 */
public class PNG {
    private int width;
    private int height;
    private List<Pixel> imagePixel;

    public PNG(int width, int height, List<Pixel> imagePixel) {
        this.width = width;
        this.height = height;
        this.imagePixel = imagePixel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Pixel> getImagePixel() {
        return imagePixel;
    }

    public void setImagePixel(List<Pixel> imagePixel) {
        this.imagePixel = imagePixel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PNG png = (PNG) o;
        return width == png.width &&
                height == png.height &&
                Objects.equals(imagePixel, png.imagePixel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, imagePixel);
    }
}
