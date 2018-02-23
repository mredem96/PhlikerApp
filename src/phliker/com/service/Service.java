package phliker.com.service;

import javafx.scene.image.Image;
import phliker.com.model.FlickrResponse;
import phliker.com.model.Photo;

import java.io.IOException;

/**
 * A service interface for the Phliker application.
 * The service may be a real service, or a service
 * which modifies some other underlying service (for example
 * providing a cache layer).
 * 
 * @author  Sarvar Nishonboyev
 *
 */
public interface Service {


    /**
     * Return the FlickrResponse object that keeps data returned from API
     * @return the object of FlickrResponse
     */
    public FlickrResponse searchPhoto(String tags) throws IOException;

    /**
     * Return photo object from the service (photo object has id, farmid, server, Image etc)
     *
     * @param photo the requested photo
     * @return the image
     */
	public Image getImage(Photo photo) throws IOException;
}