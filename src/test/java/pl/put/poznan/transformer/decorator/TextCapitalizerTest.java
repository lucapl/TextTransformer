package pl.put.poznan.transformer.logic.decorator;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextCapitalizerTest {

    private static class MockTextConverter implements TextConverter {
        @Override
        public String convert(String text) {
            // Implement the conversion logic for the mock TextConverter
            // Return the converted text
            return text;
        }
    }

    @Test
    public void testTrueConvert() {
        // Create a TextConverter instance to be wrapped
        TextConverter textConverter = new MockTextConverter();

        // Create a TextCapitalizer instance
        TextCapitalizer capitalizer = new TextCapitalizer(textConverter);

        // Test case: input "hello world"
        String input = "hello world";
        String expectedOutput = "HELLO WORLD";
        String actualOutput = capitalizer.trueConvert(input);

        // Verify the result
        assertEquals(expectedOutput, actualOutput);
    }
}

