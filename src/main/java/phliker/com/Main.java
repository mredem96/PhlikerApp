package phliker.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * <h1>Philiker Desktop App</h1>
 * This App for searching images from Flickr API
 * It retrieves 20 photos from API and display in imageFrame
 * Opened images caches in a memory in order to avoid reloading
 * <p>
 * <b>Note:</b> This requires internet connection to connect to the server
 *
 *
 * @author  Saydullo
 * @version 1.0
 * @since   2018-02-22
 */

public class Main extends Application {

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method is used to start our FX Application
     * @param primaryStage this our main frame
     *
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainStage = primaryStage;

        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("PhotoView.fxml"));
        primaryStage.setTitle("Phliker");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.show();
    }
}
