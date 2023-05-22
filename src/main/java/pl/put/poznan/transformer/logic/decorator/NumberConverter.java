package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

public abstract class NumberConverter extends TextDecorator{
    public static String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    public static String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty","ninety"};
    public static String[] fractions = {"", "tenths", "hundredths", "thousandths"};

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public NumberConverter(TextConverter wrappee) {
        super(wrappee);
    }

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */


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
            return "minus " + convertNumber(number = -number);
        }
        if (number >= 1000000) {
            return convertNumber(number / 1000000) + " million " + (number % 1000000 != 0 ?  convertNumber(number %= 1000000) : "");
        }
        if (number >= 1000) {
            return convertNumber(number / 1000) + " thousand " + (number % 1000 != 0 ? convertNumber(number %= 1000) : "");
        }
        if (number >= 100) {
            return convertNumber(number / 100) + " hundred " + (number % 100 != 0 ? convertNumber(number %= 100) : "");
        }
        if (number >= 20) {
            return tens[number / 10] + " " + (number % 10 != 0 ?  convertNumber(number %= 10) : "");
        }
        if (number >= 1) {
            return ones[number];
        }
        return "ERROR";
    }
}