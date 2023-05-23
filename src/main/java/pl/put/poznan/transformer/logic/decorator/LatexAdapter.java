package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

import java.util.HashMap;
import java.util.Map;

public class LatexAdapter extends TextDecorator{

    private Map<String,String> latexExpressions;

    public LatexAdapter(TextConverter wrappee){
        super(wrappee);
        latexExpressions = new HashMap<>();
        latexExpressions.put("&","\\&");
        latexExpressions.put("$","\\$");
    }

    @Override
    public String trueConvert(String text){
        for(var entry: latexExpressions.entrySet()){
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}
