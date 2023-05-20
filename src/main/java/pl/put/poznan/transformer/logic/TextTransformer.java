package pl.put.poznan.transformer.logic;

/**
 * Controls the program logic, creates the decorated converters based on the transforms
 */
public class TextTransformer {

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
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}
