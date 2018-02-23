package phliker.com.model;

import org.json.JSONObject;

/**
 * <h1>FLickr Response</h1>
 *It has only one JSONObject other works I did in PhotoController class
 *
 * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */
public class FlickrResponse {


    // it has only one JSONObject
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public FlickrResponse() {
    }

    private JSONObject jsonObject;

}
