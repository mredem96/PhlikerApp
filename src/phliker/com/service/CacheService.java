package phliker.com.service;

import javafx.scene.image.Image;
import phliker.com.model.FlickrResponse;
import phliker.com.model.Photo;

import java.io.IOException;
import java.util.List;

/**
 * <h1>CacheService - For caching image</h1>
 * <p>~
 *<b>Note : </b> It used List method to store images
 *
 * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */
public class CacheService implements Service {

    private List<Image> images;
    private Service flickrService;

    public CacheService() {
        flickrService = new FlickrService();

    }

    /**
     * This method resend tag to FlickrService for searching
     *
     * @param tags string searched text
     * @exception IOException On input error.
     * @see IOException,
     * */
    @Override
    public FlickrResponse searchPhoto(String tags) throws IOException {
        return flickrService.searchPhoto(tags);
    }

    // I didn't used this method
    @Override
    public Image getImage(Photo photo) {
        return null;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}