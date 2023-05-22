package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

public abstract class NumberConverter extends TextDecorator{
    public static String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    public static String[] tens = {"", "teen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty","ninety"};
    public static String[] fractions = {"", "tenths", "hundredths", "thousandths"};

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public NumberConverter(TextConverter wrappee) {
        super(wrappee);
    }

    @Override
    public abstract String trueConvert(String text);

    String convertNumber(int number) {
        if (number >= 1000000000){
            return "Number too big to convert. Please choose below 1 billion.";
        }
        if (number == 0) {
            return "zero";
        }
        String result = "";
        if (number < 0) {
            result += "minus ";
            number = -number;
        }
        if (number >= 1000000) {
            result += convertNumber(number / 1000000) + " million " + convertNumber(number %= 1000000);
        }
        if (number >= 1000) {
            result += convertNumber(number / 1000) + " thousand " + convertNumber(number %= 1000);
        }
        if (number >= 100) {
            result += convertNumber(number / 100) + " hundred " + convertNumber(number %= 100);
        }
        if (number >= 20) {
            result += tens[number / 10] + " " + convertNumber(number %= 10);
        }
        if (number >= 1) {
            result += ones[number] + " ";
        }
        return result;
    }
}
