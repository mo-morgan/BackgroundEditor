package model.ui.util;

import java.util.Objects;

/**
 * A pixel has hue, saturation, luminance, alpha, all ranging from 0.0-1.0
 */
public class Pixel {
    private double hue;
    private double sat;
    private double lum;
    private double alp;

    public Pixel() {
        hue = 0;
        sat = 0;
        lum = 1;
        alp = 1;
    }

    public Pixel(double hue, double sat, double lum, double alp) {
        this.hue = hue;
        this.sat = sat;
        this.lum = lum;
        this.alp = alp;
    }

    public double getHue() {
        return hue;
    }

    public double getSat() {
        return sat;
    }

    public double getLum() {
        return lum;
    }

    public double getAlp() {
        return alp;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }

    public void setSat(double sat) {
        this.sat = sat;
    }

    public void setLum(double lum) {
        this.lum = lum;
    }

    public void setAlp(double alp) {
        this.alp = alp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return Double.compare(pixel.hue, hue) == 0 &&
                Double.compare(pixel.sat, sat) == 0 &&
                Double.compare(pixel.lum, lum) == 0 &&
                Double.compare(pixel.alp, alp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hue, sat, lum, alp);
    }
}
