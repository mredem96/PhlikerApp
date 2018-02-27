package phliker.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import phliker.com.model.Photo;
import phliker.com.model.Photos;
import phliker.com.service.CacheService;
import phliker.com.service.FlickrService;
import phliker.com.utils.AppProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoController class for controll UI and its actions
 * Contains actions for buttons and display  retrieved image
 *
 * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */

public class PhotoController {
    // view elements
    @FXML
    TextField searchField;
    @FXML
    ImageView imageView;
    @FXML
    ImageView loaderImageView;
    @FXML
    Button prevButton;
    @FXML
    Button nextButton;
    @FXML
    Label title;
    @FXML
    Label counter;


    CacheService cacheService;
    FlickrService flickrService;
    private boolean debugging;
    private static int page;
    Photos photos;

    /**
     * This is constructor for PhotoController Class
     * It's used for initialising variables and configure settings
     * */
    public PhotoController() throws FileNotFoundException {
        debugging = AppProperties.getBool("debug");

        cacheService = new CacheService();

        photos = new Photos();
        flickrService = new FlickrService();
        if (debugging) {
            System.out.println("[debug] PhotoController: constructor");
        }

        List<Image> imageList = new ArrayList<>();
        cacheService.setImages(imageList);

    }

    /**
     * This method for searching photos from Flickr API
     * It takes string tag from textField and gives to the CacheServise class
     * And takes JsonObject and retrieve image from url and display
     *
     * @param event takes event action
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException,
     * @see JSONException
     * */
    @FXML
    private void searchImage(ActionEvent event)  {
        // takes tag from textfield
        String search_tag = searchField.getText();
        if (search_tag.equals("")){
            return;
        }
        
        // for multiple word combine to One
        search_tag = search_tag.replaceAll(" ", "_");

   

        JSONObject jsobj = null;
        try {
            jsobj = cacheService.searchPhoto(search_tag).getJsonObject(); // takes json object
        } catch (IOException e) {
            e.printStackTrace();
            title.setText("Unable to connect to the Server ....");
        }

        // for not returning enough json data to display
        
       JSONArray jsonArray = jsobj.getJSONObject("photos").getJSONArray("photo");

        if(jsonArray.length() < 19){ // if it's not enough stop actions
            title.setText(" There no enough matching results .... ");
            return;
        }
        
        // page is opened page number it initialise every search result to 0
        page = 0;
        if(photos.getPhotos() != null) {
            photos.getPhotos().clear(); // if photos results not null clear it for other results
            System.out.println("it's cleared. size : " + photos.getPhotos().size());
        }


        jsonModelParser(jsobj);

        if(cacheService.getImages() != null) {
            // if cache is not null clears it for new images
            cacheService.getImages().clear();
            System.out.println("it's cleared" + cacheService.getImages().size());
        }
        try {
            displayAndCacheImage(page);
        } catch (IOException e) {
            e.printStackTrace();
            title.setText("Unable to connect to the Server ....");
        }

        checkButtonStatus(page);
    }

    /**
     * This method for next button action. It's adds page number to 1
     * And retrieve image according to the page number
     *
     * If Image is not in cache it loads from url, otherwise it gets from cache
     *
     * @param event Action event
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException
     * */

    @FXML
    public void nextImage(ActionEvent event)  {

        page++;
        checkButtonStatus(page);

        // if page number is lower that cache list size, means it's not cached yet
        if(cacheService.getImages().size() < page+1) {
            try {
                displayAndCacheImage(page);
            } catch (IOException e) {
                e.printStackTrace();
                title.setText("Unable to connect to the Server ....");
            }
        }else {
            displayCachedImage(page);
        }
    }

    /**
     * This method for prev button action. It's decreases page number to 1
     * And retrieve image according to the page number
     *
     * In this case image would be always cached, so loads from cacheList
     *
     * @param event Action event
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException
     * */

    @FXML
    public void prevImage(ActionEvent event) throws IOException {
        page--;
        checkButtonStatus(page);
        displayCachedImage(page);

    }

    /**
     * This method used fot taking JsonObject and parse to the object models
     * It loads JsonObject to JSONArray and parse to the PHOTO objects
     * And add to the list of Photo objects
     *
     * @param jsonObject jsonObject from Flickr API
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException
     * */
    public void jsonModelParser(JSONObject jsonObject){

        List<Photo> photosList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
        for (int i = 0; i <jsonArray.length(); i++) {
            String ID = jsonArray.getJSONObject(i).getString("id");
            String secret = jsonArray.getJSONObject(i).getString("secret");
            String server = jsonArray.getJSONObject(i).getString("server");
            int farm = jsonArray.getJSONObject(i).getInt("farm");
            String title = jsonArray.getJSONObject(i).getString("title");
            String farm_str = Integer.toString(farm);
           // System.out.println("photo" + i + " : "+ title);
            photosList.add(i, new Photo(ID, secret, server, farm_str, title));
        }
        photos.setPhotos(photosList);
    }

    /**
     * This method for displaying an Image and adding to the ImageList for cache purpose
     * Takes page number and according page number retrieve Image from flickrServise
     * Displays Image and adds to cacheServise 's ImageList
     *
     * @param page Int value of page
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException
     * */
    public void displayAndCacheImage(int page) throws IOException {

        Image image = flickrService.getImage(photos.getPhotos().get(page));
        imageView.setImage(image);
        title.setText(photos.getPhotos().get(page).getTitle());
        cacheService.getImages().add(image);
    }


    /**
     * This method for displaying cached Image
     * Takes page number and retrieve from Image list in cacheService
     *
     * @param page Int value of page
     * @return NOTHING.
     * @exception IOException On input error.
     * @see IOException
     * */
    public void displayCachedImage(int page){
        Image image =  cacheService.getImages().get(page);
        imageView.setImage(image);
        title.setText(photos.getPhotos().get(page).getTitle());

    }

    /**
     *This method checks page number and update title and button status
     * If it's in left end disable prev Button, and vice-verse
     *
     * @param page int page number
     * @return NOTHING.
     */
    public void checkButtonStatus(int page){
        if(page == 0){
            counter.setText(" " + (page+1) + " of 20 images ");
            prevButton.setDisable(true);
            nextButton.setDisable(false);
        }else if(page == 19){
            counter.setText(" "+ (page+1) + " of 20 images ");
            prevButton.setDisable(false);
            nextButton.setDisable(true);
        }else {
            counter.setText(" "+ (page+1)+" of 20 images ");
            prevButton.setDisable(false);
            nextButton.setDisable(false);
        }
    }

}
