package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

/**
 * Wrapper for the TextConverter class
 */
public abstract class TextDecorator implements TextConverter {

    /**
     * Wrapped text converter
     */
    protected TextConverter wrappee;

    /**
     * Creates an instance of the class, with another text converter inside
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public TextDecorator(TextConverter wrappee){
        this.wrappee = wrappee;
    }

    /**
     * Converts the method according to the converter type.
     * Additionally, runs this method on the wrappee.
     * @param text input
     * @return text converter by all the wrappees
     */
    @Override
    public String convert(String text){
        return wrappee.convert(text);
    }
}
