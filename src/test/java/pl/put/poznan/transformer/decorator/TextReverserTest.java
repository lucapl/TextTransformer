package pl.put.poznan.transformer.logic.decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Conversions;
import pl.put.poznan.transformer.logic.TextConverter;

public class TextReverserTest {

    @Test
    public void testReverse() {
        // Create a mock TextConverter
        TextConverter mockConverter = new TextConverter() {
            @Override
            public String convert(String text) {
                return text;  // Simply return the original text
            }
        };

        // Create a TextReverser with the REVERSE option
        TextReverser reverser = new TextReverser(mockConverter, Conversions.REVERSE);

        // Test a sample input
        String input = "Hello, World!";
        String expectedOutput = "!dlroW ,olleH";
        String actualOutput = reverser.convert(input);

        // Assert that the actual output matches the expected output
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testReversePreserveCase() {
        // Create a mock TextConverter
        TextConverter mockConverter = new TextConverter() {
            @Override
            public String convert(String text) {
                return text;  // Simply return the original text
            }
        };

        // Create a TextReverser with the REVERSE_PRESERVE_CASE option
        TextReverser reverser = new TextReverser(mockConverter, Conversions.REVERSE_PRESERVE_CASE);

        // Test a sample input
        String input = "Hello, World!";
        String expectedOutput = "!DLROw ,OLLEh";
        String actualOutput = reverser.convert(input);

        // Assert that the actual output matches the expected output
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
