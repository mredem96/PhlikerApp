package phliker.com.model;

/**
 * <h1>Photo model for storing photo as object</h1>
 * It has fields of photo attributes from json object
 *
 * * @author Saydullo
 * @version 1.0
 * @since 2018-02-22
 */

public class Photo {
    private String id;
    private String secret;
    private String server;
    private String farm;
    private String title;

    public Photo(String id, String secret, String server, String farm, String title) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}