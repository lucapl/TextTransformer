package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

public class Acronymizer extends TextDecorator {

    private AcronymManager manager;

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrapee a text converter which is part of the decorator pipeline
     */
    Acronymizer(TextDecorator wrapee) {
        super(wrapee);
        manager = new AcronymManager();
    }

    public void setAcronymManager(AcronymManager man) {
        manager = man;
    }

    @Override
    public String trueConvert(String text) {
        // Perform acronymization using the AcronymManager
        return manager.acronymize(text);
    }
}

