package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.List;

/**
 * Controls the program logic, creates the decorated converters based on the transforms
 */
public class TextTransformer {
    /**
     * Maps transformations to textConverters
     */
    private TextConverterFactory textConverterFactory = new TextConverterFactory();

    /**
     * The transformations to be performed based on the request
     */
    private List<String> transforms;

    /**
     * Creates the transformer
     * @param transforms to be performed on text
     */
    public TextTransformer(List<String> transforms){
        this.transforms = transforms;
    }

    /**
     * for loggin information
     */
    private static final Logger logger = LoggerFactory.getLogger(TextTransformer.class);

    /**
     * Transforms the text
     * @param text initial text
     * @return transformed text
     */
    public String transform(String text){
        logger.debug("Requesting a new text converter");
        TextConverter textConverter = textConverterFactory.createConverter(transforms);
        logger.debug("Requesting text conversion from the converter");
        return textConverter.convert(text);
    }

    public void setTextConverterFactory(TextConverterFactory textConverterFactory){
        this.textConverterFactory = textConverterFactory;
    }

    public void setTransforms(List<String> transforms){
        this.transforms = transforms;
    }
}
