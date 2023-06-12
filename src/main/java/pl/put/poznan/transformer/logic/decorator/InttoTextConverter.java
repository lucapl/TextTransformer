package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

import static java.lang.Integer.parseInt;

public class InttoTextConverter extends NumberConverter {

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public InttoTextConverter(TextConverter wrappee) {
        super(wrappee);
    }

    @Override
    public String trueConvert(String text) {
        String[] parts = text.split(" ");
        String result = "";
        for (String part : parts) {
            try {
                int number = parseInt(part);
                result += convertNumber(number) + " ";
            } catch (NumberFormatException e) {
                result += part + " ";
            }
        }

        return result;
    }

    // TODO: Implement the conversion logic for converting an integer number to text representation
    // The logic should handle various cases such as negative numbers, special cases, and general conversion rules

}
