package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcronymUnwinder extends TextDecorator {

    private AcronymManager manager;

    AcronymUnwinder(TextConverter wrapee) {
        super(wrapee);
        manager = new AcronymManager();
    }

    void setAcronymManager(AcronymManager man) {
        manager = man;
    }

    @Override
    public String trueConvert(String text) {
        return manager.unwindAcronyms(text);
    }
}
