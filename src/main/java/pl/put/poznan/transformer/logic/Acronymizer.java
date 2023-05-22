package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acronymizer extends TextDecorator {

    private AcronymManager manager;

    Acronymizer(TextDecorator wrapee) {
        super(wrapee);
        manager = new AcronymManager();
    }

    @Override
    public String trueConvert(String text) {
        return manager.acronimize(text);
    }
}
