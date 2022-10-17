package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WorkerEntity;

import javax.persistence.*;
import java.io.File;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            URL url = new File("src/main/resources/scenes/start.fxml").toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Start");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
