package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acronymizer extends TextDecorator {

    private HashMap<String, String> acronyms;

    Acronymizer(TextDecorator wrapee) {
        super(wrapee);
        acronyms = new AcronymManager().getAcronyms();
    }

    @Override
    public String trueConvert(String text) {
        String result = text;
        for (Map.Entry<String, String> acronym : acronyms.entrySet()) {
            String pattern = "(?i)" + acronym.getKey();
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(result);
            result = matcher.replaceAll(acronym.getValue());
        }
        return result;
    }
}
