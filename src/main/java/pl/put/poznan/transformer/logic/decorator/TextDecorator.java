package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

/**
 * Wrapper for the TextConverter class
 */
public abstract class TextDecorator implements TextConverter {

    /**
     * Wrapped text converter
     */
    private TextConverter wrappee;

    /**
     * Creates an instance of the class, with another text converter inside
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public TextDecorator(TextConverter wrappee){
        this.setWrappee(wrappee);
    }

    /**
     * Calls method *trueConvert*, should not be overridden
     * After converting, runs this method on the wrappee.
     * @param text input
     * @return text converted by all the wrappees
     */
    @Override
    public String convert(String text){
        return getWrappee().convert((text));
    }

    /**
     * The method that the decorator calls to actually convert the text
     * @param text input
     * @return converted text
     */
    public abstract String trueConvert(String text);

    public void setWrappee(TextConverter wrappee) {
        this.wrappee = wrappee;
    }

    private TextConverter getWrappee() {
        return wrappee;
    }
}
