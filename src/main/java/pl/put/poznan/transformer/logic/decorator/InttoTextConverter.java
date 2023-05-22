package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

import static java.lang.Integer.parseInt;

public class InttoTextConverter extends NumberConverter{

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

//    private String convertNumber(int number) {
//        if (number >= 1000000000){
//            return "Number too big to convert. Please choose below 1 billion.";
//        }
//        if (number == 0) {
//            return "zero";
//        }
//        String result = "";
//        if (number < 0) {
//            result += "minus ";
//            number = -number;
//        }
//        if (number >= 1000000) {
//            result += convertNumber(number / 1000000) + " million " + convertNumber(number %= 1000000);
//        }
//        if (number >= 1000) {
//            result += convertNumber(number / 1000) + " thousand " + convertNumber(number %= 1000);
//        }
//        if (number >= 100) {
//            result += convertNumber(number / 100) + " hundred " + convertNumber(number %= 100);
//        }
//        if (number >= 20) {
//            result += tens[number / 10] + " " + convertNumber(number %= 10);
//        }
//        if (number >= 1) {
//            result += ones[number] + " ";
//        }
//        return result;
//    }
}
