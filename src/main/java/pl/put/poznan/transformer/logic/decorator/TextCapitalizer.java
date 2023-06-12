package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.Conversions;
import pl.put.poznan.transformer.logic.TextConverter;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class TextCapitalizer extends TextDecorator {


    /**
     * Creates an instance of the class, with another text component inside
     *
     * @param textComponent a text component which is part of the decorator pipeline
     */
    public TextCapitalizer(TextComponent textComponent) {
        super(textComponent);
    }

    @Override
    public String trueConvert(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String transformedWord = transformWord(word);
            result.append(transformedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Transforms a word based on the specified option
     *
     * @param word the word to transform
     * @return the transformed word
     */
    private String transformWord(String word) {
        switch (option) {
            case CASE_UPPER:
                return word.toUpperCase();
            case CASE_LOWER:
                return word.toLowerCase();
            case CASE_CAPITAL:
                return capitalizeWord(word);
            default:
                return word;
        }
    }

    /**
     * Capitalizes a word by making the first letter uppercase and the rest lowercase
     *
     * @param word the word to capitalize
     * @return the capitalized word
     */
    private String capitalizeWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}

