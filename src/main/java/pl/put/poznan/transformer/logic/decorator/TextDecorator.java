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
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public TextDecorator(TextConverter wrappee) {
        this.setWrappee(wrappee);
    }

    /**
     * Calls the "trueConvert" method and then passes the converted text to the wrappee for further conversion.
     * Should not be overridden.
     *
     * @param text input text
     * @return text converted by all the wrappees
     */
    @Override
    public String convert(String text) {
        text = trueConvert(text);
        if (wrappee != null) {
            text = getWrappee().convert(text);
        }
        return text;
    }

    /**
     * The method that the decorator calls to actually convert the text.
     *
     * @param text input text
     * @return converted text
     */
    public abstract String trueConvert(String text);

    /**
     * Sets the wrappee text converter.
     *
     * @param wrappee a text converter to be wrapped
     */
    public void setWrappee(TextConverter wrappee) {
        this.wrappee = wrappee;
    }

    /**
     * Returns the wrappee text converter.
     *
     * @return the wrappee text converter
     */
    private TextConverter getWrappee() {
        return wrappee;
    }
}

