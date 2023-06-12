package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcronymUnwinder extends TextDecorator {

    private AcronymManager manager;

    /**
     * Initializes the AcronymUnwinder with a TextConverter and creates an instance of AcronymManager.
     *
     * @param wrappee the TextConverter instance to be wrapped by the decorator
     */
    AcronymUnwinder(TextConverter wrappee) {
        super(wrappee);
        manager = new AcronymManager();
    }

    @Override
    public String trueConvert(String text) {
        return manager.unwindAcronyms(text);
    }
}

