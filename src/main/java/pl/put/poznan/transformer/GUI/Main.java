package pl.put.poznan.transformer.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI.fxml"));
        BorderPane page = (BorderPane) loader.load();

        Controller controller = loader.getController();

        Scene scene = new Scene(page);

        primaryStage.setTitle("TextTransformer GUI");
        primaryStage.setScene(scene);

        controller.preSetup();

        primaryStage.setOnShown(event -> {
            controller.setup();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


