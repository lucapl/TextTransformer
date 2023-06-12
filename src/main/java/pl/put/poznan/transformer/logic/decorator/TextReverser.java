package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.Conversions;
import pl.put.poznan.transformer.logic.TextConverter;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class TextReverser extends TextDecorator {

    private Conversions option;

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     * @param option  the type of reversal operation to perform
     */
    public TextReverser(TextConverter wrappee, Conversions option) {
        super(wrappee);
        this.option = option;
    }

    @Override
    public String trueConvert(String text) {
        StringBuilder reversedText = new StringBuilder(text);
        String reverse = reversedText.reverse().toString();

        switch (option) {
            case REVERSE:
                return reverse;
            case REVERSE_PRESERVE_CASE:
                return reversePreserveCase(text, reverse);
        }
        return null;
    }

    /**
     * Reverses the case of a character
     *
     * @param c the character to reverse the case of
     * @return the character with the case reversed
     */
    private char reverseCharacterCase(char c) {
        return c;
    }

    /**
     * Reverses the text while preserving the case of each character
     *
     * @param original the original text
     * @param reversed the reversed text
     * @return the reversed text with preserved character case
     */
    private String reversePreserveCase(String original, String reversed) {
        return reversed;
    }
}
