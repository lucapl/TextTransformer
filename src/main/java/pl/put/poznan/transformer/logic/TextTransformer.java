package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

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
    private final String[] transforms;

    /**
     * Creates the transformer
     * @param transforms to be performed on text
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    /**
     * Transforms the text
     * @param text initial text
     * @return transformed text
     */
    public String transform(String text){
        TextConverter textConverter = textConverterFactory.createConverter(transforms);
        return textConverter.convert(text);
    }
}
