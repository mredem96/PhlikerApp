package phliker.com.model;

import java.util.List;

/**
 * <h1>This class for saving Photo List</h1>
 *It has one List<Photo> field
 *
 * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */
public class Photos {

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    private List<Photo> photos;

    public Photos() {
    }

/*
    modify this class. Think about how it might look. It is depend on JSON response structure
    */
}