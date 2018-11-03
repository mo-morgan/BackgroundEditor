package model.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.ui.util.PNG;

/**
 * A folder containing a bunch of PNG
 */
public class Folder {
    private String name;
    private List<PNG> album;

    public Folder(String name) {
        this.name = name;
        album = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PNG> getAlbum() {
        return album;
    }


    /**
     * @param img   the image to add to the album
     *              Can contain duplicates
     */
    public void addPicture(PNG img) {
        album.add(img);
    }

    public int size() {
        return album.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(name, folder.name) &&
                Objects.equals(album, folder.album);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, album);
    }
}
