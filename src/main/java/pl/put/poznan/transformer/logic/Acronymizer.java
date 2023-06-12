package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class Acronymizer extends TextDecorator {

    private AcronymManager manager;

    Acronymizer(TextDecorator wrapee) {
        super(wrapee);
        manager = new AcronymManager();
    }

    public void setAcronymManager(AcronymManager man) {
        manager = man;
    }

    @Override
    public String trueConvert(String text) {
        return manager.acronymize(text);
    }
}
