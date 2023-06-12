package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * This class represents the main entry point of the Text Transformer application.
 * It controls the program flow by launching the REST API using Spring Boot.
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    /**
     * The main method of the Text Transformer application.
     * It launches the REST API using Spring Boot.
     * @param args The command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
