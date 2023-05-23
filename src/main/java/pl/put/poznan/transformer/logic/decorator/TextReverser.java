package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.Conversions;
import pl.put.poznan.transformer.logic.TextConverter;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class TextReverser extends TextDecorator {

    private Conversions option;
    public TextReverser(TextConverter wrappee, Conversions option) {
        super(wrappee);
        this.option = option;
    }

    @Override
    public String trueConvert(String text) {
        StringBuilder reversedText = new StringBuilder(text);
        String reverse = reversedText.reverse().toString();
        /*
        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText.append(reverseCharacterCase(text.charAt(i)));
        }

        return reversedText.toString();*/
        switch (option){
            case REVERSE: return reverse;
            case REVERSE_PRESERVE_CASE: return reversePreserveCase(text,reverse);
        }
        return null;
    }

    private char reverseCharacterCase(char c) {
        if (Character.isUpperCase(c)) {
            return Character.toLowerCase(c);
        } else if (Character.isLowerCase(c)) {
            return Character.toUpperCase(c);
        }
        return c;
    }

    private String reversePreserveCase(String original,String reversed){
        StringBuilder casePreserved = new StringBuilder();
        for(int i = 0; i < original.length(); i++){
            char org_c = original.charAt(i);
            char rev_c = reversed.charAt(i);
            casePreserved.append(Character.isUpperCase(org_c)?Character.toUpperCase(rev_c):Character.toLowerCase(rev_c));
        }
        return casePreserved.toString();
    }
}