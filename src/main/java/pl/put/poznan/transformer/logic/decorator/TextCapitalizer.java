package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.Conversions;
import pl.put.poznan.transformer.logic.TextConverter;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class TextCapitalizer extends TextDecorator {

    private Conversions option;
    public TextCapitalizer(TextConverter wrappee,Conversions option) {
        super(wrappee);
        this.option = option;
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

    private String capitalizeWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
