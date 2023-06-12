package pl.put.poznan.transformer.logic.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextDecoratorTest {
    private TextDecorator textDecorator;
    private TextConverter wrappee;

    @BeforeEach
    public void setup(){
        wrappee = mock(TextConverter.class);
        when(wrappee.convert(anyString())).thenReturn("wrappee called");
        textDecorator = new TextDecorator(wrappee) {
            @Override
            public String trueConvert(String text) {
                return text;
            }
        };
    }

    @Test
    public void testDecoratorPattern(){
        String text = "test";
        assertEquals("wrappee called",textDecorator.convert(text));
        verify(wrappee).convert(text);
    }
}