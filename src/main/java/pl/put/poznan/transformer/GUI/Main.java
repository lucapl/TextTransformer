package pl.put.poznan.transformer.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage=primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI.fxml"));
        BorderPane page = (BorderPane) loader.load();

        Controller controller = loader.getController();

        Scene scene = new Scene(page);

        primaryStage.setTitle("TextTransformer GUI");
        primaryStage.setScene(scene);

        primaryStage.setOnShown(event -> {
            controller.setup();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

