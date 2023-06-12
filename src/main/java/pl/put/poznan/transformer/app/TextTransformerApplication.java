package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Controls REST api flow
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    /**
     * Launches the REST api using springboot
     * @param args program arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
