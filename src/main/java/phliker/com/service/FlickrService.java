package phliker.com.service;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import phliker.com.model.FlickrResponse;
import phliker.com.model.Photo;
import phliker.com.utils.AppProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * <h1>Flickr - For connecting to Flickr API</h1>
 * <p>
 *<b>Note : </b> It connects flickr API and requires internet connection
 *uses method flickr.photos.search for searching
 *
 * @apiNote https://api.flickr.com/services/rest - API server
 * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */
public class FlickrService implements Service {

    private static final String SEARCH = "flickr.photos.search";
    private static final String GET_INFO = "flickr.photos.getInfo";
    private final static String flickrApiUrl = "https://api.flickr.com/services/rest";
    private static String flickrApiKey;
    private static String photoSourceUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg";
    private boolean debugging;

    public FlickrService() {
        flickrApiKey = AppProperties.getProperty("api_key");
    }

    /**
     * search photo method - for searching photo using given tag
     * It takes string tag and send given url to the server
     * Takes json data as a respond and returns as FlickrResponse object
     *
     * @param tags
     * @return flickrResponse - FlickrResponse object
     * @throws  IOException On input error.
     * @see IOException
     * @throws  JSONException On response error.
     * @see JSONException
     */
    @Override
    public FlickrResponse searchPhoto(String tags) throws IOException, JSONException {
        // url patterm
        String url = "{SERVER}/?method={METHOD}&api_key={APIKEY}&tags={TAG}&safe_search=1&per_page=20&format=json&nojsoncallback=1";
        // changing with our variables
        url = url.replace("{SERVER}", flickrApiUrl);
        url = url.replace("{METHOD}", SEARCH);
        url = url.replace("{APIKEY}", flickrApiKey);
        url = url.replace("{TAG}", tags);
        JSONObject json = readJsonFromUrl(url);
        System.out.println(url);
        //System.out.println(json.toString());
        FlickrResponse flickrResponse = new FlickrResponse();
        flickrResponse.setJsonObject(json);
        return flickrResponse;
    }


    /**
     * This method for getting Image from url
     *
     * @param photo Photo object
     * @return image  JavaFX Image object
     * @throws  IOException On input error.
     * @see IOException
     * */
    @Override
    public Image getImage(Photo photo) throws IOException {
        String id = photo.getId();
        String farmId = photo.getFarm();
        String secret = photo.getSecret();
        String serverId = photo.getServer();

        String source = photoSourceUrl;
        source = source.replace("{farm-id}", farmId);
        source = source.replace("{server-id}", serverId);
        source = source.replace("{id}", id);
        source = source.replace("{secret}", secret);

        System.out.println(source);
        BufferedImage img = ImageIO.read(new URL(source));
        // It reads BufferedImage from url and we should convert it Image object
        Image image = SwingFXUtils.toFXImage(img, null );
        return image;
    }


    /**
     * This method for read json from url
     *
     * @param url String for connecting to the server
     * @return json   JSONObject which retrieved from server
     * @throws  IOException On input error.
     * @see IOException
     * @throws  JSONException On response error.
     * @see JSONException
     * */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        JSONObject json = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
           // System.out.println(jsonText);
            json = new JSONObject(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return json;
    }

    /**
     *This method for reading json text from Json object properly
     *
     * @param rd - Reader obeject for reading json object properly
     * @return string
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


}