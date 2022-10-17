package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class NewWindow {
    private static Parent root;
    private static Stage stage;

    public static void setNewWindow(String fxml, ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/scenes/" + fxml).toURI().toURL();
        root = FXMLLoader.load(url);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
