package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

public class RepeatsRemover extends TextDecorator{

    public RepeatsRemover(TextConverter wrappee){
        super(wrappee);
    }

    @Override
    public String trueConvert(String text){
        String[] words = text.split(" ");
        String lastWord = null;
        StringBuilder result = new StringBuilder();
        for (var word : words){
            if(!word.equals(lastWord)){
                result.append(word).append(" ");
            }
            lastWord = word;
        }
        return result.toString();
    }
}
