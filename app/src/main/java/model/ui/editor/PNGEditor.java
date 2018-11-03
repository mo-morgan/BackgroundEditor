package model.ui.editor;

import model.ui.util.PNG;

/**
 * A functor class for editing images
 */
public class PNGEditor {

    /**
     * Sets the saturation of the image's pixels to 0 and returns the image
     *
     * @param image  image to grayscale
     * @return       the grayscaled image
     */
    public static PNG grayscale(PNG image) {

        return image;
    }

    /**
     * Dims the image's pixels with respect to the center of the image,
     *   the farther pixel is from the center, the darker it becomes
     *
     * @param image  image to spotlight
     * @return       the spotlighed image
     */
    public static PNG spotlight(PNG image, int centerX, int centerY) {

        return image;
    }

    /**
     * Edits the image to make the image look "sketched"
     *
     * @param image  image to sketchify
     * @return       the sketchified image
     */
    public static PNG sketchify(PNG image) {

        return image;
    }

    /**
     * The hue of every pixel of the image is set to either orange or red,
     *  based on if the value of the hue is closer to orange or red
     *
     * @param image  image to fallify
     * @return       the fallified image
     */
    public static PNG fallify(PNG image) {

        return image;
    }
}
