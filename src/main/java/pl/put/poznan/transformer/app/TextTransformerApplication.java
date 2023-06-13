package pl.put.poznan.transformer.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pl.put.poznan.transformer.GUI.Controller;
import pl.put.poznan.transformer.GUI.Main;


/**
 * Controls REST api flow
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication extends javafx.application.Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextTransformerApplication.primaryStage=primaryStage;

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
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new
                SpringApplicationBuilder(TextTransformerApplication.class);
        builder.application()
                .setWebApplicationType(WebApplicationType.NONE);
    }

    /**
     * Launches the REST api using springboot
     * @param args program arguments
     */
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                SpringApplication.run(TextTransformerApplication.class, args);
            }
        };

        thread.run();
        launch(args);
    }
}
