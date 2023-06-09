package pl.put.poznan.transformer.logic.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.TextConverter;
import pl.put.poznan.transformer.logic.TextTransformer;

public abstract class NumberConverter extends TextDecorator{
    public static String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine","ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    public static String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty","ninety"};
    public static String[] fractions = {"", "tenths", "hundredths", "thousandths"};

    private static final Logger logger = LoggerFactory.getLogger(NumberConverter.class);

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public NumberConverter(TextConverter wrappee) {
        super(wrappee);
    }

    String convertNumber(int number) {
        if (number >= 1000000000){
            logger.info("Number too big to convert. Please choose below 1 billion.");
            return Integer.toString(number);
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
        logger.info("some error with number conversion");
        return Integer.toString(number);
    }
}
