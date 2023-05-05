package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

public abstract class TextDecorator implements TextConverter {
    protected TextConverter wrappee;

    public TextDecorator(TextConverter wrappee){
        this.wrappee = wrappee;
    }

    @Override
    public String convert(String text){
        return wrappee.convert(text);
    }
}
